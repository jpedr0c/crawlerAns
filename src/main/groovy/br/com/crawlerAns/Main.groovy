package br.com.crawlerAns

class Main {
    static void main(String[] args) {
        println("Iniciando crawler...")

        String linkPrestador = TissCrawler.obterLinkPrestador("https://www.gov.br/ans")

        println("\nLink prestador:")
        println(linkPrestador)

        String linkTiss = TissCrawler.obterLinkTiss(linkPrestador)

        println("\nLink Tiss:")
        println(linkTiss)

        String linkVersaoTiss = TissCrawler.obterLinkVersaoTiss(linkTiss)

        println("\nLink da versão do Tiss:")
        println(linkVersaoTiss)

        String linkComponenteComunicacao = TissCrawler.obterLinkComponenteComunicacao(linkVersaoTiss)

        println("\nLink do componente de comunicação:")
        println(linkComponenteComunicacao)

        if (linkComponenteComunicacao) {
            BaixarArquivos.baixarArquivo(linkComponenteComunicacao, "./Downloads/Arquivos_padrao_TISS/")
        }

    }
}
