package br.com.crawlerAns

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class TissCrawler {

    static Document acessarPagina(String url) {
        try {
            Document document = Jsoup.connect(url).get()
            return document
        } catch (Exception e) {
            println "Erro ao acessar site: ${e.message}"
        }
        return null
    }

    static String obterLinkPorTexto(Document doc, String textoAlvo) {
        try {
            if (doc == null) {
                throw new IllegalArgumentException("Documento HTML é nulo.")
            }

            if (textoAlvo == null || textoAlvo.trim().isEmpty()) {
                throw new IllegalArgumentException("Texto alvo é inválido ou vazio.")
            }

            Element foundLink = doc.select("a:contains(${textoAlvo})").first()

            if (foundLink == null) {
                throw new RuntimeException("Link contendo '" + textoAlvo + "' não encontrado.")
            }

            String href = foundLink.attr("abs:href")

            if (href == null || href.isEmpty()) {
                throw new RuntimeException("Link encontrado, mas o href é nulo ou vazio.")
            }

            return href

        } catch (Exception e) {
            println("Erro ao extrair link: " + e.getMessage())
        }

        return null
    }

    static String obterLinkPrestador(String url) {
        try {
            Document doc = acessarPagina(url)
            return obterLinkPorTexto(doc, "Espaço do Prestador de Serviços de Saúde")
        } catch (Exception e) {
            println "Erro ao obter o link da página do Prestador de Serviços: ${e.message}"
        }
        return null
    }

    static String obterLinkTiss(String url) {
        try {
            Document doc = acessarPagina(url)
            return obterLinkPorTexto(doc, "Tiss")
        } catch (Exception e) {
            println "Erro ao obter o link da página do TISS: ${e.message}"
        }
        return null
    }

    static String obterLinkVersaoTiss(String url) {
        try {
            Document doc = acessarPagina(url)
            return obterLinkPorTexto(doc, "Clique aqui para acessar a versão ")
        } catch (Exception e) {
            println "Erro ao obter o link da versão do TISS: ${e.message}"
        }
        return null
    }

    static String obterLinkComponenteComunicacao(String url) {
        try {
            Document doc = acessarPagina(url)
            return obterLinkPorTexto(doc, "Componente de comunicação")
        } catch (Exception e) {
            println "Erro ao obter o link do Componente de Comunicação: ${e.message}"
        }
        return null
    }

    static String obterLinkTabelasRelacionadas(String url) {
        try {
            Document doc = acessarPagina(url)
            return obterLinkPorTexto(doc, "Clique aqui para acessar as planilhas")
        } catch (Exception e) {
            println "Erro ao obter o link das tabelas relacionadas: ${e.message}"
        }
        return null
    }

    static String obterLinkTabelaErros(String url) {
        try {
            Document doc = acessarPagina(url)
            return obterLinkPorTexto(doc, "Clique aqui para baixar a tabela de erros no envio para a ANS")
        } catch (Exception e) {
            println "Erro ao obter o link da tabela de erros: ${e.message}"
        }
        return null
    }

    static String obterLinkHistoricoVersoes(String url) {
        try {
            Document doc = acessarPagina(url)
            return obterLinkPorTexto(doc, "Clique aqui para acessar todas as versões dos Componentes")
        } catch (Exception e) {
            println "Erro ao obter o link do histórico de versões do TISS: ${e.message}"
        }
        return null
    }

    static List<String[]> extrairDadosHistorico(Document doc) {
        List<String[]> dados = new ArrayList<>()
        try {
            Elements linhas = doc.select("table tbody tr")
            for (Element linha : linhas) {
                Elements colunas = linha.select("td")
                if (colunas.size() >= 3) {
                    String competencia = colunas.get(0).text().trim()
                    String publicacao  = colunas.get(1).text().trim()
                    String vigencia    = colunas.get(2).text().trim()

                    if (competencia.matches("(?i)[a-zá-ú]{3}/\\d{4}")) {
                        int ano = competencia.split("/")[1].toInteger()
                        if (ano >= 2016) {
                            dados.add([competencia, publicacao, vigencia] as String[])
                        }
                    }
                }
            }
        } catch (Exception e) {
            println "Erro ao extrair dados: ${e.message}"
        }
        return dados
    }
}
