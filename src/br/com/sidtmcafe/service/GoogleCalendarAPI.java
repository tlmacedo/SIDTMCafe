package br.com.sidtmcafe.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Lists;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

import static br.com.sidtmcafe.interfaces.Constants.*;

public class GoogleCalendarAPI {

    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"), ".store/calendar_sample");

    private static FileDataStoreFactory dataStoreFactory;

    private static HttpTransport httpTransport;

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static com.google.api.services.calendar.Calendar client;

    static final java.util.List<Calendar> addedCalendarsUsingBatch = Lists.newArrayList();

    private static Credential getAutorizacao() throws Exception {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(GoogleCalendarAPI.class.getResourceAsStream(GOOGLE_CALENDAR_KEY)));
        if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println(
                    "Enter Client ID and Secret from https://code.google.com/apis/console/?api=calendar "
                            + "into calendar-cmdline-sample/src/main/resources/client_secrets.json");
            System.exit(1);
        }
        // set up authorization code flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets,
                Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    private static void carregarGoogleCalendar() {
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
            Credential credential = getAutorizacao();
            client = new com.google.api.services.calendar.Calendar.Builder(
                    httpTransport, JSON_FACTORY, credential).setApplicationName(GOOGLE_CALENDAR_APPLICATION_NAME).build();


        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static Calendar getCalendar() throws IOException {
        carregarGoogleCalendar();
        return client.calendars().get(GOOGLE_CALENDAR_PADRAO).execute();
    }

    public static void addEventoCalendar(String tituloEvento, DateTime dtInicio, DateTime dtFim, String location) throws IOException {
        Event event = new Event();
        event.setSummary(tituloEvento);
        event.setLocation(location);
        event.setStart(new EventDateTime().setDateTime(dtInicio));
        event.setEnd(new EventDateTime().setDateTime(dtFim));

        client.events().insert(getCalendar().getId(), event).execute();
    }


//    public static void main(String[] args) {
//        try {
//            // initialize the transport
//            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//
//            // initialize the data store factory
//            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
//
//            // authorization
//            Credential credential = authorize();
//
//            // set up global Calendar instance
//            client = new com.google.api.services.calendar.Calendar.Builder(
//                    httpTransport, JSON_FACTORY, credential).setApplicationName(GOOGLE_CALENDAR_APPLICATION_NAME).build();
//
//            // run commands
//            showCalendars();
////            addCalendarsUsingBatch();
//            Calendar calendar = client.calendars().get(GOOGLE_CALENDAR_PADRAO).execute();
////            updateCalendar(calendar);
//            addEvent(calendar);
////            showEvents(calendar);
////            deleteCalendarsUsingBatch();
////            deleteCalendar(calendar);
//
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        System.exit(1);
//    }
//
//    private static void showCalendars() throws IOException {
//        View.header("Show Calendars");
//        CalendarList feed = client.calendarList().list().execute();
//        View.display(feed);
//    }
//
//    private static void addCalendarsUsingBatch() throws IOException {
//        View.header("Add Calendars using Batch");
//        BatchRequest batch = client.batch();
//
//        // Create the callback.
//        JsonBatchCallback<Calendar> callback = new JsonBatchCallback<Calendar>() {
//
//            @Override
//            public void onSuccess(Calendar calendar, HttpHeaders responseHeaders) {
//                View.display(calendar);
//                addedCalendarsUsingBatch.add(calendar);
//            }
//
//            @Override
//            public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
//                System.out.println("Error Message: " + e.getMessage());
//            }
//        };
//
//        // Create 2 Calendar Entries to insert.
//        Calendar entry1 = new Calendar().setSummary("Calendar for Testing 1");
//        client.calendars().insert(entry1).queue(batch, callback);
//
//        Calendar entry2 = new Calendar().setSummary("Calendar for Testing 2");
//        client.calendars().insert(entry2).queue(batch, callback);
//
//        batch.execute();
//    }
//
//    private static Calendar addCalendar() throws IOException {
//        View.header("Pegendo Calendario");
//        //Calendar entry = new Calendar();
////        entry.setSummary("Calendar for Testing 3");
//        Calendar result = client.calendars().get("65q1ae60dbon58epveq5e0qq14@group.calendar.google.com").execute();
//
//        System.out.println(result.getSummary());
////        Calendar result = client.calendarList().get("65q1ae60dbon58epveq5e0qq14@group.calendar.google.com");
//        View.display(result);
//        return result;
//    }
//
//    private static Calendar updateCalendar(Calendar calendar) throws IOException {
//        View.header("Update Calendar");
//        Calendar entry = new Calendar();
//        entry.setSummary("Updated Calendar for Testing");
//        Calendar result = client.calendars().patch(calendar.getId(), entry).execute();
//        View.display(result);
//        return result;
//    }
//
//
//    private static void addEvent(Calendar calendar) throws IOException {
//        View.header("teste para cobranca");
//        Event event = newEvent();
//        Event result = client.events().insert(calendar.getId(), event).execute();
//        View.display(result);
//    }
//
//    private static Event newEvent() {
//        Event event = new Event();
//        event.setSummary("Almocar logo logo");
//        Date startDate = new Date();
//        Date endDate = new Date(startDate.getTime() + 3600000);
//        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
//        event.setStart(new EventDateTime().setDateTime(start));
//        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
//        event.setEnd(new EventDateTime().setDateTime(end));
//        return event;
//    }
//
//    private static void showEvents(Calendar calendar) throws IOException {
//        View.header("Show Events");
//        Events feed = client.events().list(calendar.getId()).execute();
//        View.display(feed);
//    }
//
//    private static void deleteCalendarsUsingBatch() throws IOException {
//        View.header("Delete Calendars Using Batch");
//        BatchRequest batch = client.batch();
//        for (Calendar calendar : addedCalendarsUsingBatch) {
//            client.calendars().delete(calendar.getId()).queue(batch, new JsonBatchCallback<Void>() {
//
//                @Override
//                public void onSuccess(Void content, HttpHeaders responseHeaders) {
//                    System.out.println("Delete is successful!");
//                }
//
//                @Override
//                public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
//                    System.out.println("Error Message: " + e.getMessage());
//                }
//            });
//        }
//
//        batch.execute();
//    }
//
//    private static void deleteCalendar(Calendar calendar) throws IOException {
//        View.header("Delete Calendar");
//        client.calendars().delete(calendar.getId()).execute();
//    }
}