package br.com.capisoft.arvores.services;

import br.com.capisoft.arvores.models.Arvore;
import br.com.capisoft.arvores.models.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Busca {

    private static Logger LOG = LoggerFactory.getLogger(Busca.class);

    public static Node binariaDaArvore(Arvore arvore, String textoDoNodeBuscado){
        LOG.info("----------------------------------------------");
        LOG.info("INICIANDO BUSCA BINÁRIA NA "+arvore.toString());
        Node no = buscaBinaria(arvore.getRoot(), textoDoNodeBuscado);
        if (no == null) {
            LOG.info("BUSCA BINARIA | NADA ENCONTRADO | Node \""+textoDoNodeBuscado.toUpperCase()+"\" NÃO encontrado na árvore.");
        } else {
            LOG.info("BUSCA BINARIA | NADA ENCONTRADO | Node \""+textoDoNodeBuscado.toUpperCase()+"\" foi encontrado na árvore.");
        }
        return no;
    }

    public static Node binariaNoNode(Node node, String textoDoNodeBuscado){
        LOG.info("----------------------------------------------");
        LOG.info("VOU REALIZAR UMA BUSCA BINÁRIA NA ARVORE "+node.toString());
        Node no = buscaBinaria(node, textoDoNodeBuscado);
        if (no == null) {
            LOG.info("BUSCA BINARIA | NADA ENCONTRADO | Node \""+textoDoNodeBuscado.toUpperCase()+"\" NÃO encontrado a partir do Node \""+node.getTexto().toUpperCase()+"\"\n----------------------------------------------\n");
        } else {
            LOG.info("BUSCA BINARIA | NADA ENCONTRADO | Node \""+textoDoNodeBuscado.toUpperCase()+"\" foi encontrado a partir do Node \""+node.getTexto().toUpperCase()+"\"\n----------------------------------------------\n");
        }
        return no;
    }

    private static Node buscaBinaria(Node node, String texto){
        LOG.info("BUSCA BINARIA | Buscando \""+texto.toUpperCase()+"\", a partir do Node \""+node.toString()+"\"");

        if (node.getTexto().equals(texto)){
            LOG.info("BUSCA BINARIA | NODE ENCONTRADO! | "+node);
            return node;
        }
        int res = node.getTexto().compareTo(texto);
        if (res == 0){
            LOG.info("BUSCA BINARIA | NODE ENCONTRADO! | "+node);
            return node;
        }
        if (res < 0){
            if (node.contemNoEsquerdo()){
                LOG.info("BUSCA BINARIA | <<< ESQUERDA <<< | verificando na esquerda do \""+node+"\"");
                buscaBinaria(node.getNoEsquerdo(), texto);
            }
        } else {
            if (node.contemNoDireito()){
                LOG.info("BUSCA BINARIA | >>> DIREITA >>> | verificando na direita do \""+node+"\"");
                buscaBinaria(node.getNoDireito(), texto);
            }
        }
        return null;
    }
}
