package br.com.crawlerAns

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class SalvarArquivos {
    static void baixarArquivo(String urlArquivo, String pastaDestino) {
        try {
            String nomeArquivo = urlArquivo.tokenize('/').last()
            String caminhoCompleto = "${pastaDestino}${nomeArquivo}"

            Files.createDirectories(Paths.get(pastaDestino))

            URL url = new URL(urlArquivo)
            Files.copy(
                    url.openStream(),
                    Paths.get(caminhoCompleto),
                    StandardCopyOption.REPLACE_EXISTING
            )
            println("Arquivo baixado com sucesso: ${caminhoCompleto}")
        } catch (Exception e) {
            println("Erro ao baixar o arquivo: ${e.message}")
            e.printStackTrace()
        }
    }

    static void salvarArquivoEmCsv(List<String[]> dados, String pastaDestino) {
        try {
            Files.createDirectories(Paths.get(pastaDestino).parent)
            new File(pastaDestino).withWriter('UTF-8') { writer ->
                writer.writeLine("Competência;Publicação;Início de Vigência")
                dados.each { String[] linha ->
                    writer.writeLine(linha.join(";"))
                }
            }
            println "CSV gerado com sucesso: ${pastaDestino}"
        } catch (Exception e) {
            println "Erro ao gerar CSV: ${e.message}"
        }
    }
}
