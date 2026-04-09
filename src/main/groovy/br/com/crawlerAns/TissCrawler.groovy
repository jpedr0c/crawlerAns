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
}
