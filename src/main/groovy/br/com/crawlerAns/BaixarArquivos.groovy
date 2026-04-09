package br.com.crawlerAns

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.net.URL

class BaixarArquivos {
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
}
