## Árvore Original

```mermaid
graph TD
idx10((10))--> idx7((7))
idx7 -.-> idx10
idx10((10))--> idx18((18))
idx18 -.-> idx10
idx18((18))--> idx15((15))
idx15 -.-> idx18
idx18((18))--> idx20((20))
idx20 -.-> idx18
idx15((15))--> idx14((14))
idx14 -.-> idx15
idx15((15))--> idx16((16))
idx16 -.-> idx15
idx20((20))--> idx19((19))
idx19 -.-> idx20
idx20((20))--> idx22((22))
idx22 -.-> idx20

style idx18 fill:#FF6666,stroke:#333,stroke-width:2px
style idx19 fill:#CCFF66,stroke:#333,stroke-width:2px
```

1. Identificando o sucessor
```java
arvore_a.remover(18);
no <- arvore_a.buscar(18);
arvore_a.remover(no);
no_sucessor <- idx18.sucessor(no);
```

2. Realizando a transposição

```java

```