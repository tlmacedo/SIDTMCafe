<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://ws.fontededados.com.br/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" targetNamespace="http://ws.fontededados.com.br/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://ws.fontededados.com.br/">
      <s:element name="Bonus">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BonusResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BonusResult" type="tns:RetornoNumerico" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoNumerico">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="1" maxOccurs="1" name="Valor" type="s:decimal" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="BaseRetorno">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="MensagemErro" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ValorCobrado" type="s:decimal" />
          <s:element minOccurs="1" maxOccurs="1" name="CodErro" type="s:int" />
        </s:sequence>
      </s:complexType>
      <s:element name="Saldo">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SaldoResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SaldoResult" type="tns:RetornoNumerico" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Cep">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cep" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CepResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CepResult" type="tns:RetornoCep" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoCep">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Endereco" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Bairro" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Cidade" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Estado" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Cep" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="ProtestoPF">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cpf" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ProtestoPFResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ProtestoPFResult" type="tns:RetornoProtesto" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoProtesto">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="NumeroInscricao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Situacao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Detalhes" type="tns:ArrayOfProtestoInfo" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ArrayOfProtestoInfo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="ProtestoInfo" nillable="true" type="tns:ProtestoInfo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ProtestoInfo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Estado" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Mensagem" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="ProtestoPJ">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cnpj" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ProtestoPJResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ProtestoPJResult" type="tns:RetornoProtesto" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SintegraCNPJ">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cnpj" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="estado" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SintegraCNPJResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SintegraCNPJResult" type="tns:RetornoSintegra" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoSintegra">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Cnpj" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="InscricaoEstadual" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="RazaoSocial" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Logradouro" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Numero" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Complemento" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Bairro" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Municipio" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="UF" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CEP" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="AtividadeEconomica" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="SituacaoCadastral" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataSituacaoCadastral" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="RegimeApuracao" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="SituacaoCadastralPF">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cpf" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="dataNascimento" type="s:dateTime" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SituacaoCadastralPFResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SituacaoCadastralPFResult" type="tns:RetornoSituacaoCadastralPF" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoSituacaoCadastralPF">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Nome" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Cpf" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="SituacaoCadastral" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CodigoControle" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataHora" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataNascimento" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataInscricao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DigitoVerificador" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="NomePorCPF">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cpf" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="NomePorCPFResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="NomePorCPFResult" type="tns:RetornoNomePF" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoNomePF">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Cpf" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Nome" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="CertidaoRFCnpj">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cnpj" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CertidaoRFCnpjResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CertidaoRFCnpjResult" type="tns:RetornoCertidaoRFCnpj" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoCertidaoRFCnpj">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Cnpj" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Nome" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Situacao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataHora" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Validade" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Raw64" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="CertidaoSPCcm">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cnpj" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CertidaoSPCcmResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CertidaoSPCcmResult" type="tns:RetornoCertidaoSPCcm" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoCertidaoSPCcm">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Cnpj" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Situacao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Raw64" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="SituacaoCadastralPJ">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cnpj" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SituacaoCadastralPJResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SituacaoCadastralPJResult" type="tns:RetornoSituacaoCadastralPJ" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoSituacaoCadastralPJ">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="CNPJ" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataAbertura" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="NomeEmpresa" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="NomeFantasia" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="AtividadePrincipal" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="AtividadeSecundaria" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Atividades" type="tns:ArrayOfString" />
              <s:element minOccurs="0" maxOccurs="1" name="NaturezaJuridica" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Logradouro" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Numero" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Complemento" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CEP" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Bairro" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Municipio" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="UF" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="SituacaoCadastral" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataSituacaoCadastral" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="MotivoSituacaoCadastral" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="SituacaoEspecial" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataSituacaoEspecial" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="EnderecoEletronico" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Telefone" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ArrayOfString">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="string" nillable="true" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="Nfe">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="chave" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="NfeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="NfeResult" type="tns:RetornoNfe" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoNfe">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="ChaveAcesso" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="NumeroNFe" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="VersaoXml" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Numero" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Serie" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataEmissao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ValorTotalNotaFiscal" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CnpjEmitente" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="NomeEmitente" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="InscricaoEstadualEmitente" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="UFEmitente" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CnpjDestinatario" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="NomeDestinatario" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="InscricaoEstadualDestinatario" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="UFDestinatario" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Processo" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="VersaoProcesso" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="TipoEmissao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Finalidade" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="NaturezaOperacao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="TipoOperacao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="FormaPagamento" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DigestValue" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Ocorrencia" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Protocolo" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DataHora" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="RecebimentoAmbienteNacional" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ProdutoServico" type="tns:ArrayOfProdutoServicoInfo" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ArrayOfProdutoServicoInfo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="ProdutoServicoInfo" nillable="true" type="tns:ProdutoServicoInfo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ProdutoServicoInfo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="Num" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Descricao" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Qtd" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Valor" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CodigoProduto" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CodigoNCM" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CFOP" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="IndicadorComposicaoValorTotalNF" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CodigoEANComercial" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="UnidadeComercial" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="QuantidadeComercial" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="CodigoEANTributavel" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="UnidadeTributavel" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="QuantidadeTributavel" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ValorUnitarioComercializacao" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ValorUnitarioTributacao" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="OrigemMercadoria" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="TributacaoICMS" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ValorBCIcmsSTRetido" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ValorIcmsSTretido" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BaseCalculoICMSNormal" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="AliquotaICMSNormal" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ValorICMSNormal" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BaseCalculo" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="Aliquota" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ValorIPI" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="DenatranRenavam">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cpfCnpj" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="renavam" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="DenatranRenavamResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="DenatranRenavamResult" type="tns:RetornoDenatranRenavam" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoDenatranRenavam">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Placa" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Renavam" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CpfCnpj" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="NomeProprietario" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Tipo" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Especie" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Carroceria" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Categoria" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Combustivel" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="MarcaModelo" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="AnoFabricacao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="AnoModelo" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Cor" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Lotacao" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CapacidadeCarga" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Potencia" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Cilindradas" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Restricao1" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Restricao2" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Restricao3" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="Restricao4" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="SimplesNacional">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="login" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="senha" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cnpj" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SimplesNacionalResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SimplesNacionalResult" type="tns:RetornoSimplesNacional" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="RetornoSimplesNacional">
        <s:complexContent mixed="false">
          <s:extension base="tns:BaseRetorno">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="Cnpj" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="NomeEmpresarial" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="OpcaoSimplesNacional" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="OpcaoSIMEI" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="RetornoNumerico" nillable="true" type="tns:RetornoNumerico" />
      <s:element name="RetornoCep" nillable="true" type="tns:RetornoCep" />
      <s:element name="RetornoProtesto" nillable="true" type="tns:RetornoProtesto" />
      <s:element name="RetornoSintegra" nillable="true" type="tns:RetornoSintegra" />
      <s:element name="RetornoSituacaoCadastralPF" nillable="true" type="tns:RetornoSituacaoCadastralPF" />
      <s:element name="RetornoNomePF" nillable="true" type="tns:RetornoNomePF" />
      <s:element name="RetornoCertidaoRFCnpj" nillable="true" type="tns:RetornoCertidaoRFCnpj" />
      <s:element name="RetornoCertidaoSPCcm" nillable="true" type="tns:RetornoCertidaoSPCcm" />
      <s:element name="RetornoSituacaoCadastralPJ" nillable="true" type="tns:RetornoSituacaoCadastralPJ" />
      <s:element name="RetornoNfe" nillable="true" type="tns:RetornoNfe" />
      <s:element name="RetornoDenatranRenavam" nillable="true" type="tns:RetornoDenatranRenavam" />
      <s:element name="RetornoSimplesNacional" nillable="true" type="tns:RetornoSimplesNacional" />
    </s:schema>
  </wsdl:types>
  <wsdl:message name="BonusSoapIn">
    <wsdl:part name="parameters" element="tns:Bonus" />
  </wsdl:message>
  <wsdl:message name="BonusSoapOut">
    <wsdl:part name="parameters" element="tns:BonusResponse" />
  </wsdl:message>
  <wsdl:message name="SaldoSoapIn">
    <wsdl:part name="parameters" element="tns:Saldo" />
  </wsdl:message>
  <wsdl:message name="SaldoSoapOut">
    <wsdl:part name="parameters" element="tns:SaldoResponse" />
  </wsdl:message>
  <wsdl:message name="CepSoapIn">
    <wsdl:part name="parameters" element="tns:Cep" />
  </wsdl:message>
  <wsdl:message name="CepSoapOut">
    <wsdl:part name="parameters" element="tns:CepResponse" />
  </wsdl:message>
  <wsdl:message name="ProtestoPFSoapIn">
    <wsdl:part name="parameters" element="tns:ProtestoPF" />
  </wsdl:message>
  <wsdl:message name="ProtestoPFSoapOut">
    <wsdl:part name="parameters" element="tns:ProtestoPFResponse" />
  </wsdl:message>
  <wsdl:message name="ProtestoPJSoapIn">
    <wsdl:part name="parameters" element="tns:ProtestoPJ" />
  </wsdl:message>
  <wsdl:message name="ProtestoPJSoapOut">
    <wsdl:part name="parameters" element="tns:ProtestoPJResponse" />
  </wsdl:message>
  <wsdl:message name="SintegraCNPJSoapIn">
    <wsdl:part name="parameters" element="tns:SintegraCNPJ" />
  </wsdl:message>
  <wsdl:message name="SintegraCNPJSoapOut">
    <wsdl:part name="parameters" element="tns:SintegraCNPJResponse" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPFSoapIn">
    <wsdl:part name="parameters" element="tns:SituacaoCadastralPF" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPFSoapOut">
    <wsdl:part name="parameters" element="tns:SituacaoCadastralPFResponse" />
  </wsdl:message>
  <wsdl:message name="NomePorCPFSoapIn">
    <wsdl:part name="parameters" element="tns:NomePorCPF" />
  </wsdl:message>
  <wsdl:message name="NomePorCPFSoapOut">
    <wsdl:part name="parameters" element="tns:NomePorCPFResponse" />
  </wsdl:message>
  <wsdl:message name="CertidaoRFCnpjSoapIn">
    <wsdl:part name="parameters" element="tns:CertidaoRFCnpj" />
  </wsdl:message>
  <wsdl:message name="CertidaoRFCnpjSoapOut">
    <wsdl:part name="parameters" element="tns:CertidaoRFCnpjResponse" />
  </wsdl:message>
  <wsdl:message name="CertidaoSPCcmSoapIn">
    <wsdl:part name="parameters" element="tns:CertidaoSPCcm" />
  </wsdl:message>
  <wsdl:message name="CertidaoSPCcmSoapOut">
    <wsdl:part name="parameters" element="tns:CertidaoSPCcmResponse" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPJSoapIn">
    <wsdl:part name="parameters" element="tns:SituacaoCadastralPJ" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPJSoapOut">
    <wsdl:part name="parameters" element="tns:SituacaoCadastralPJResponse" />
  </wsdl:message>
  <wsdl:message name="NfeSoapIn">
    <wsdl:part name="parameters" element="tns:Nfe" />
  </wsdl:message>
  <wsdl:message name="NfeSoapOut">
    <wsdl:part name="parameters" element="tns:NfeResponse" />
  </wsdl:message>
  <wsdl:message name="DenatranRenavamSoapIn">
    <wsdl:part name="parameters" element="tns:DenatranRenavam" />
  </wsdl:message>
  <wsdl:message name="DenatranRenavamSoapOut">
    <wsdl:part name="parameters" element="tns:DenatranRenavamResponse" />
  </wsdl:message>
  <wsdl:message name="SimplesNacionalSoapIn">
    <wsdl:part name="parameters" element="tns:SimplesNacional" />
  </wsdl:message>
  <wsdl:message name="SimplesNacionalSoapOut">
    <wsdl:part name="parameters" element="tns:SimplesNacionalResponse" />
  </wsdl:message>
  <wsdl:message name="BonusHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BonusHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoNumerico" />
  </wsdl:message>
  <wsdl:message name="SaldoHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SaldoHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoNumerico" />
  </wsdl:message>
  <wsdl:message name="CepHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cep" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CepHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoCep" />
  </wsdl:message>
  <wsdl:message name="ProtestoPFHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cpf" type="s:string" />
  </wsdl:message>
  <wsdl:message name="ProtestoPFHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoProtesto" />
  </wsdl:message>
  <wsdl:message name="ProtestoPJHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="ProtestoPJHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoProtesto" />
  </wsdl:message>
  <wsdl:message name="SintegraCNPJHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
    <wsdl:part name="estado" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SintegraCNPJHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoSintegra" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPFHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cpf" type="s:string" />
    <wsdl:part name="dataNascimento" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPFHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoSituacaoCadastralPF" />
  </wsdl:message>
  <wsdl:message name="NomePorCPFHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cpf" type="s:string" />
  </wsdl:message>
  <wsdl:message name="NomePorCPFHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoNomePF" />
  </wsdl:message>
  <wsdl:message name="CertidaoRFCnpjHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CertidaoRFCnpjHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoCertidaoRFCnpj" />
  </wsdl:message>
  <wsdl:message name="CertidaoSPCcmHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CertidaoSPCcmHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoCertidaoSPCcm" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPJHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPJHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoSituacaoCadastralPJ" />
  </wsdl:message>
  <wsdl:message name="NfeHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="chave" type="s:string" />
  </wsdl:message>
  <wsdl:message name="NfeHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoNfe" />
  </wsdl:message>
  <wsdl:message name="DenatranRenavamHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cpfCnpj" type="s:string" />
    <wsdl:part name="renavam" type="s:string" />
  </wsdl:message>
  <wsdl:message name="DenatranRenavamHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoDenatranRenavam" />
  </wsdl:message>
  <wsdl:message name="SimplesNacionalHttpGetIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SimplesNacionalHttpGetOut">
    <wsdl:part name="Body" element="tns:RetornoSimplesNacional" />
  </wsdl:message>
  <wsdl:message name="BonusHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BonusHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoNumerico" />
  </wsdl:message>
  <wsdl:message name="SaldoHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SaldoHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoNumerico" />
  </wsdl:message>
  <wsdl:message name="CepHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cep" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CepHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoCep" />
  </wsdl:message>
  <wsdl:message name="ProtestoPFHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cpf" type="s:string" />
  </wsdl:message>
  <wsdl:message name="ProtestoPFHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoProtesto" />
  </wsdl:message>
  <wsdl:message name="ProtestoPJHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="ProtestoPJHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoProtesto" />
  </wsdl:message>
  <wsdl:message name="SintegraCNPJHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
    <wsdl:part name="estado" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SintegraCNPJHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoSintegra" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPFHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cpf" type="s:string" />
    <wsdl:part name="dataNascimento" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPFHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoSituacaoCadastralPF" />
  </wsdl:message>
  <wsdl:message name="NomePorCPFHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cpf" type="s:string" />
  </wsdl:message>
  <wsdl:message name="NomePorCPFHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoNomePF" />
  </wsdl:message>
  <wsdl:message name="CertidaoRFCnpjHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CertidaoRFCnpjHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoCertidaoRFCnpj" />
  </wsdl:message>
  <wsdl:message name="CertidaoSPCcmHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CertidaoSPCcmHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoCertidaoSPCcm" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPJHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SituacaoCadastralPJHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoSituacaoCadastralPJ" />
  </wsdl:message>
  <wsdl:message name="NfeHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="chave" type="s:string" />
  </wsdl:message>
  <wsdl:message name="NfeHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoNfe" />
  </wsdl:message>
  <wsdl:message name="DenatranRenavamHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cpfCnpj" type="s:string" />
    <wsdl:part name="renavam" type="s:string" />
  </wsdl:message>
  <wsdl:message name="DenatranRenavamHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoDenatranRenavam" />
  </wsdl:message>
  <wsdl:message name="SimplesNacionalHttpPostIn">
    <wsdl:part name="login" type="s:string" />
    <wsdl:part name="senha" type="s:string" />
    <wsdl:part name="cnpj" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SimplesNacionalHttpPostOut">
    <wsdl:part name="Body" element="tns:RetornoSimplesNacional" />
  </wsdl:message>
  <wsdl:portType name="ConsultaSoap">
    <wsdl:operation name="Bonus">
      <wsdl:input message="tns:BonusSoapIn" />
      <wsdl:output message="tns:BonusSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Saldo">
      <wsdl:input message="tns:SaldoSoapIn" />
      <wsdl:output message="tns:SaldoSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Cep">
      <wsdl:input message="tns:CepSoapIn" />
      <wsdl:output message="tns:CepSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ProtestoPF">
      <wsdl:input message="tns:ProtestoPFSoapIn" />
      <wsdl:output message="tns:ProtestoPFSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ProtestoPJ">
      <wsdl:input message="tns:ProtestoPJSoapIn" />
      <wsdl:output message="tns:ProtestoPJSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SintegraCNPJ">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Consulta disponível para os Estados(AL, BA, ES, GO, SE, SP)</wsdl:documentation>
      <wsdl:input message="tns:SintegraCNPJSoapIn" />
      <wsdl:output message="tns:SintegraCNPJSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPF">
      <wsdl:input message="tns:SituacaoCadastralPFSoapIn" />
      <wsdl:output message="tns:SituacaoCadastralPFSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="NomePorCPF">
      <wsdl:input message="tns:NomePorCPFSoapIn" />
      <wsdl:output message="tns:NomePorCPFSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CertidaoRFCnpj">
      <wsdl:input message="tns:CertidaoRFCnpjSoapIn" />
      <wsdl:output message="tns:CertidaoRFCnpjSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CertidaoSPCcm">
      <wsdl:input message="tns:CertidaoSPCcmSoapIn" />
      <wsdl:output message="tns:CertidaoSPCcmSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPJ">
      <wsdl:input message="tns:SituacaoCadastralPJSoapIn" />
      <wsdl:output message="tns:SituacaoCadastralPJSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Nfe">
      <wsdl:input message="tns:NfeSoapIn" />
      <wsdl:output message="tns:NfeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="DenatranRenavam">
      <wsdl:input message="tns:DenatranRenavamSoapIn" />
      <wsdl:output message="tns:DenatranRenavamSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SimplesNacional">
      <wsdl:input message="tns:SimplesNacionalSoapIn" />
      <wsdl:output message="tns:SimplesNacionalSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="ConsultaHttpGet">
    <wsdl:operation name="Bonus">
      <wsdl:input message="tns:BonusHttpGetIn" />
      <wsdl:output message="tns:BonusHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="Saldo">
      <wsdl:input message="tns:SaldoHttpGetIn" />
      <wsdl:output message="tns:SaldoHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="Cep">
      <wsdl:input message="tns:CepHttpGetIn" />
      <wsdl:output message="tns:CepHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="ProtestoPF">
      <wsdl:input message="tns:ProtestoPFHttpGetIn" />
      <wsdl:output message="tns:ProtestoPFHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="ProtestoPJ">
      <wsdl:input message="tns:ProtestoPJHttpGetIn" />
      <wsdl:output message="tns:ProtestoPJHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SintegraCNPJ">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Consulta disponível para os Estados(AL, BA, ES, GO, SE, SP)</wsdl:documentation>
      <wsdl:input message="tns:SintegraCNPJHttpGetIn" />
      <wsdl:output message="tns:SintegraCNPJHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPF">
      <wsdl:input message="tns:SituacaoCadastralPFHttpGetIn" />
      <wsdl:output message="tns:SituacaoCadastralPFHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="NomePorCPF">
      <wsdl:input message="tns:NomePorCPFHttpGetIn" />
      <wsdl:output message="tns:NomePorCPFHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="CertidaoRFCnpj">
      <wsdl:input message="tns:CertidaoRFCnpjHttpGetIn" />
      <wsdl:output message="tns:CertidaoRFCnpjHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="CertidaoSPCcm">
      <wsdl:input message="tns:CertidaoSPCcmHttpGetIn" />
      <wsdl:output message="tns:CertidaoSPCcmHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPJ">
      <wsdl:input message="tns:SituacaoCadastralPJHttpGetIn" />
      <wsdl:output message="tns:SituacaoCadastralPJHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="Nfe">
      <wsdl:input message="tns:NfeHttpGetIn" />
      <wsdl:output message="tns:NfeHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="DenatranRenavam">
      <wsdl:input message="tns:DenatranRenavamHttpGetIn" />
      <wsdl:output message="tns:DenatranRenavamHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SimplesNacional">
      <wsdl:input message="tns:SimplesNacionalHttpGetIn" />
      <wsdl:output message="tns:SimplesNacionalHttpGetOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="ConsultaHttpPost">
    <wsdl:operation name="Bonus">
      <wsdl:input message="tns:BonusHttpPostIn" />
      <wsdl:output message="tns:BonusHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="Saldo">
      <wsdl:input message="tns:SaldoHttpPostIn" />
      <wsdl:output message="tns:SaldoHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="Cep">
      <wsdl:input message="tns:CepHttpPostIn" />
      <wsdl:output message="tns:CepHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="ProtestoPF">
      <wsdl:input message="tns:ProtestoPFHttpPostIn" />
      <wsdl:output message="tns:ProtestoPFHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="ProtestoPJ">
      <wsdl:input message="tns:ProtestoPJHttpPostIn" />
      <wsdl:output message="tns:ProtestoPJHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SintegraCNPJ">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Consulta disponível para os Estados(AL, BA, ES, GO, SE, SP)</wsdl:documentation>
      <wsdl:input message="tns:SintegraCNPJHttpPostIn" />
      <wsdl:output message="tns:SintegraCNPJHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPF">
      <wsdl:input message="tns:SituacaoCadastralPFHttpPostIn" />
      <wsdl:output message="tns:SituacaoCadastralPFHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="NomePorCPF">
      <wsdl:input message="tns:NomePorCPFHttpPostIn" />
      <wsdl:output message="tns:NomePorCPFHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="CertidaoRFCnpj">
      <wsdl:input message="tns:CertidaoRFCnpjHttpPostIn" />
      <wsdl:output message="tns:CertidaoRFCnpjHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="CertidaoSPCcm">
      <wsdl:input message="tns:CertidaoSPCcmHttpPostIn" />
      <wsdl:output message="tns:CertidaoSPCcmHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPJ">
      <wsdl:input message="tns:SituacaoCadastralPJHttpPostIn" />
      <wsdl:output message="tns:SituacaoCadastralPJHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="Nfe">
      <wsdl:input message="tns:NfeHttpPostIn" />
      <wsdl:output message="tns:NfeHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="DenatranRenavam">
      <wsdl:input message="tns:DenatranRenavamHttpPostIn" />
      <wsdl:output message="tns:DenatranRenavamHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SimplesNacional">
      <wsdl:input message="tns:SimplesNacionalHttpPostIn" />
      <wsdl:output message="tns:SimplesNacionalHttpPostOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="ConsultaSoap" type="tns:ConsultaSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="Bonus">
      <soap:operation soapAction="http://ws.fontededados.com.br/Bonus" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Saldo">
      <soap:operation soapAction="http://ws.fontededados.com.br/Saldo" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Cep">
      <soap:operation soapAction="http://ws.fontededados.com.br/Cep" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ProtestoPF">
      <soap:operation soapAction="http://ws.fontededados.com.br/ProtestoPF" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ProtestoPJ">
      <soap:operation soapAction="http://ws.fontededados.com.br/ProtestoPJ" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SintegraCNPJ">
      <soap:operation soapAction="http://ws.fontededados.com.br/SintegraCNPJ" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPF">
      <soap:operation soapAction="http://ws.fontededados.com.br/SituacaoCadastralPF" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NomePorCPF">
      <soap:operation soapAction="http://ws.fontededados.com.br/NomePorCPF" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CertidaoRFCnpj">
      <soap:operation soapAction="http://ws.fontededados.com.br/CertidaoRFCnpj" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CertidaoSPCcm">
      <soap:operation soapAction="http://ws.fontededados.com.br/CertidaoSPCcm" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPJ">
      <soap:operation soapAction="http://ws.fontededados.com.br/SituacaoCadastralPJ" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Nfe">
      <soap:operation soapAction="http://ws.fontededados.com.br/Nfe" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="DenatranRenavam">
      <soap:operation soapAction="http://ws.fontededados.com.br/DenatranRenavam" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SimplesNacional">
      <soap:operation soapAction="http://ws.fontededados.com.br/SimplesNacional" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="ConsultaSoap12" type="tns:ConsultaSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="Bonus">
      <soap12:operation soapAction="http://ws.fontededados.com.br/Bonus" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Saldo">
      <soap12:operation soapAction="http://ws.fontededados.com.br/Saldo" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Cep">
      <soap12:operation soapAction="http://ws.fontededados.com.br/Cep" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ProtestoPF">
      <soap12:operation soapAction="http://ws.fontededados.com.br/ProtestoPF" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ProtestoPJ">
      <soap12:operation soapAction="http://ws.fontededados.com.br/ProtestoPJ" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SintegraCNPJ">
      <soap12:operation soapAction="http://ws.fontededados.com.br/SintegraCNPJ" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPF">
      <soap12:operation soapAction="http://ws.fontededados.com.br/SituacaoCadastralPF" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NomePorCPF">
      <soap12:operation soapAction="http://ws.fontededados.com.br/NomePorCPF" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CertidaoRFCnpj">
      <soap12:operation soapAction="http://ws.fontededados.com.br/CertidaoRFCnpj" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CertidaoSPCcm">
      <soap12:operation soapAction="http://ws.fontededados.com.br/CertidaoSPCcm" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPJ">
      <soap12:operation soapAction="http://ws.fontededados.com.br/SituacaoCadastralPJ" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Nfe">
      <soap12:operation soapAction="http://ws.fontededados.com.br/Nfe" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="DenatranRenavam">
      <soap12:operation soapAction="http://ws.fontededados.com.br/DenatranRenavam" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SimplesNacional">
      <soap12:operation soapAction="http://ws.fontededados.com.br/SimplesNacional" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="ConsultaHttpGet" type="tns:ConsultaHttpGet">
    <http:binding verb="GET" />
    <wsdl:operation name="Bonus">
      <http:operation location="/Bonus" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Saldo">
      <http:operation location="/Saldo" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Cep">
      <http:operation location="/Cep" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ProtestoPF">
      <http:operation location="/ProtestoPF" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ProtestoPJ">
      <http:operation location="/ProtestoPJ" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SintegraCNPJ">
      <http:operation location="/SintegraCNPJ" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPF">
      <http:operation location="/SituacaoCadastralPF" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NomePorCPF">
      <http:operation location="/NomePorCPF" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CertidaoRFCnpj">
      <http:operation location="/CertidaoRFCnpj" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CertidaoSPCcm">
      <http:operation location="/CertidaoSPCcm" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPJ">
      <http:operation location="/SituacaoCadastralPJ" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Nfe">
      <http:operation location="/Nfe" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="DenatranRenavam">
      <http:operation location="/DenatranRenavam" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SimplesNacional">
      <http:operation location="/SimplesNacional" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="ConsultaHttpPost" type="tns:ConsultaHttpPost">
    <http:binding verb="POST" />
    <wsdl:operation name="Bonus">
      <http:operation location="/Bonus" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Saldo">
      <http:operation location="/Saldo" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Cep">
      <http:operation location="/Cep" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ProtestoPF">
      <http:operation location="/ProtestoPF" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ProtestoPJ">
      <http:operation location="/ProtestoPJ" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SintegraCNPJ">
      <http:operation location="/SintegraCNPJ" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPF">
      <http:operation location="/SituacaoCadastralPF" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NomePorCPF">
      <http:operation location="/NomePorCPF" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CertidaoRFCnpj">
      <http:operation location="/CertidaoRFCnpj" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CertidaoSPCcm">
      <http:operation location="/CertidaoSPCcm" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SituacaoCadastralPJ">
      <http:operation location="/SituacaoCadastralPJ" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Nfe">
      <http:operation location="/Nfe" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="DenatranRenavam">
      <http:operation location="/DenatranRenavam" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SimplesNacional">
      <http:operation location="/SimplesNacional" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="Consulta">
    <wsdl:port name="ConsultaSoap" binding="tns:ConsultaSoap">
      <soap:address location="http://ws.fontededados.com.br/consulta.asmx" />
    </wsdl:port>
    <wsdl:port name="ConsultaSoap12" binding="tns:ConsultaSoap12">
      <soap12:address location="http://ws.fontededados.com.br/consulta.asmx" />
    </wsdl:port>
    <wsdl:port name="ConsultaHttpGet" binding="tns:ConsultaHttpGet">
      <http:address location="http://ws.fontededados.com.br/consulta.asmx" />
    </wsdl:port>
    <wsdl:port name="ConsultaHttpPost" binding="tns:ConsultaHttpPost">
      <http:address location="http://ws.fontededados.com.br/consulta.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>