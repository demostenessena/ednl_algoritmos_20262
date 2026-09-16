## Árvore Binária de Busca
---
### Remoção

---
<h4>Cenário 1</h4>
A remoção ocorre na folha

---
<h4>Cenário 2</h4>
A remoção ocorre em um nó com somente com o filho da direita.

---
<h4>Cenário 3</h4>
A remoção ocorre em um nó com somente com o filho da direita.

---
<h4>Cenário 4</h4>
A remoção ocorre em um nó com dois filhos.

Para esse cenário, pode-se implementar um método auxiliar definido como <code>Transpor(T,no_a,no_b)</code>. Esse método substitui o <code>no_a</code> pelo <code>no_b</code> na árvore <code>T</code>.
```
Transpor(T, no_antigo, no_novo) {
    SE (no_antigo.pai == NULO) ENTÃO // O nó a ser removido é a raiz da árvore
       T.raiz <- no_novo
    SENÃO SE (no_antigo.esquerda == no_antigo.pai.esquerda) ENTÃO // O nó removido é filho esquerdo
        no_antigo.pai.esquerda <- no_novo
    SENÃO // O nó removido é filho direito
        no_antigo.pai.direita <- no_novo
    FIM_SE

    SE no_novo != NULO Então
        no_novo.pai <- no_antigo.pai
    FIM_SE

}

```

<h5> 1. Remoção de um nó com antecessor não imediato</h5>

<table>
<tr>
<td>

Fig. 1 - Identificação do nó a ser removido
```mermaid
graph TD
    id1((A)) --> id2((B))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    id2 --> id4((D))
    id4 -.-> id2
    id2 --> id5((E))
    id5 -.-> id2

    style id1 fill:#FF6666,stroke:#333,stroke-width:2px
```

</td>
<td>

Fig. 2 - Identificação do antecessor
```mermaid
graph TD
    id1((A)) --> id2((B))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    id2 --> id4((D))
    id4 -.-> id2
    id2 --> id5((E))
    id5 -.-> id2

    style id1 fill:#FF6666,stroke:#333,stroke-width:2px
    style id5 fill:#CCFF66,stroke:#333,stroke-width:2px
```

</td>
</tr>

<tr>
<td>

Fig. 3 - Transposição do antecessor com o nó a ser removido
```mermaid
graph TD
    id1((E)) --> id2((B))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    id2 --> id4((D))
    id4 -.-> id2
    id2 --> id5((A))
    id5 -.-> id2

    style id5 fill:#FF6666,stroke:#333,stroke-width:2px
    style id1 fill:#CCFF66,stroke:#333,stroke-width:2px
```

</td>
<td>

Fig. 4 - Remoção do nó folha
```mermaid
graph TD
    id1((E)) --> id2((B))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    id2 --> id4((D))
    id4 -.-> id2

    style id1 fill:#CCFF66,stroke:#333,stroke-width:2px
```

</td>
</tr>
</table>

---

<h5> 2. Remoção de um nó com antecessor</h5>

<table>
<tr>
<td>

Fig. 1 - Identificação do nó a ser removido
```mermaid
graph TD
    id1((A)) --> id2((B))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    id2 --> id4((D))
    id4 -.-> id2

    style id1 fill:#FF6666,stroke:#333,stroke-width:2px
```

</td>
<td>

Fig. 2 - Identificação do antecessor
```mermaid
graph TD
    id1((A)) --> id2((B))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    id2 --> id4((D))
    id4 -.-> id2
    

    style id1 fill:#FF6666,stroke:#333,stroke-width:2px
    style id2 fill:#CCFF66,stroke:#333,stroke-width:2px
```

</td>
</tr>

<tr>
<td>

Fig. 3 - Transposição do antecessor com o nó a ser removido
```mermaid
graph TD
    id1((B)) --> id2((A))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    id2 --> id4((D))
    id4 -.-> id2
    

    style id2 fill:#FF6666,stroke:#333,stroke-width:2px
    style id1 fill:#CCFF66,stroke:#333,stroke-width:2px
```

</td>
<td>

Fig. 4 - Remoção do nó com um filho
```mermaid
graph TD
    id1((B)) --> id2((A))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    id2 --> id4((D))
    id4 -.-> id2
    

    style id2 fill:#FF6666,stroke:#333,stroke-width:2px
    style id4 fill:#CCFF66,stroke:#333,stroke-width:2px
```

</td>
</tr>


<tr>
<td>

Fig. 5 - Transposição do nó a ser removido com o único filho
```mermaid
graph TD
    id1((B)) --> id2((D))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    id2 --> id4((A))
    id4 -.-> id2
    
    style id4 fill:#FF6666,stroke:#333,stroke-width:2px
    style id2 fill:#CCFF66,stroke:#333,stroke-width:2px
```

</td>
<td>

Fig. 6 - Remover uma folha
```mermaid
graph TD
    id1((B)) --> id2((D))
    id2 -.-> id1
    id1 --> id3((C))
    id3 -.-> id1
    
    style id2 fill:#CCFF66,stroke:#333,stroke-width:2px
```

</td>
</tr>
</table>


<h5> 3. Remoção de um nó sem antecessor</h5>

<table>
<tr>
<td>

Fig. 1 - Identificação do nó a ser removido
```mermaid
graph TD
    id1((A)) --> id2((nil))
    id1 --> id3((B))
    id3 -.-> id1
    id3 --> id4((C))
    id4 -.-> id3

    style id1 fill:#FF6666,stroke:#333,stroke-width:2px
```

</td>
<td>

Fig. 2 - Identificação do sucessor


</td>
</tr>

<tr>
<td>

Fig. 3 - Transposição do sucessor com o nó a ser removido


</td>

</tr>
</table>



