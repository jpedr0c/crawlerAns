package br.com.crawlerAns

class Main {
    static void main(String[] args) {
        println("Iniciando crawler...")

        String linkPrestador = TissCrawler.obterLinkPrestador("https://www.gov.br/ans")

        String linkTiss = TissCrawler.obterLinkTiss(linkPrestador)

        String linkVersaoTiss = TissCrawler.obterLinkVersaoTiss(linkTiss)

        String linkComponenteComunicacao = TissCrawler.obterLinkComponenteComunicacao(linkVersaoTiss)

        if (linkComponenteComunicacao) {
            SalvarArquivos.baixarArquivo(linkComponenteComunicacao, "./Downloads/Arquivos_padrao_TISS/")
        }

        String linkTabelasRelacionadas = TissCrawler.obterLinkTabelasRelacionadas(linkTiss)

        String linkTabelasErros= TissCrawler.obterLinkTabelaErros(linkTabelasRelacionadas)

        if (linkTabelasErros) {
            SalvarArquivos.baixarArquivo(linkTabelasErros, "./Downloads/Tabela_erros_ANS/")
        }

        String linkHistoricoVersoes = TissCrawler.obterLinkHistoricoVersoes(linkTiss)

        List<String[]> dados = TissCrawler.extrairDadosHistorico(TissCrawler.acessarPagina(linkHistoricoVersoes))

        SalvarArquivos.salvarArquivoEmCsv(dados, "./Downloads/Historico_versoes_TISS/historico_TISS.csv")
    }
}
