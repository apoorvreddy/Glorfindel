# Glorfindel

The Glorfindel project aims to provide sentence similarity measures and fast semantic search for English sentences. This project will incorporate sophisticated similarity measures like Dependency Tree Kernels, Semantic LSA, and ConvNet based phrasal similarity measures.

Glorfindel will rely on open corpora like WordNet and Paraphrase data from MSR and Paraphrase.org.

## Currently Available Sentence Similarity Measures: ##

1) *DepTreeKernel:* Computes a kernel between dependency trees of two sentences.

2) *Jaccard:* Jaccard coefficient between word sets of two sentences.

3) *Phrasal:* Computes a score considering overlap of phrases between two sentences.


## Endpoints:
### Sentence Similarity
#### Headers
    Content-Type: application/json
#### `POST` request
    /similarity
    {
        "text1": "The lion chased the mouse in the savannah",
        "text2": "The angry lion chased the squeaky mouse",
        "similarityFunction":"DepTreeKernel"
    }
