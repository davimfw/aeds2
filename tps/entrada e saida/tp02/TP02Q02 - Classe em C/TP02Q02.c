#include <stdio.h>
#include <string.h>
#include <stdbool.h>

typedef struct Personagem
{
    /* data */
    char nome[30], corDoCabelo[20], codDaPele[20], corDosOlhos[20], anoNascimento[10], genero[20], homeWorld[30];
    int altura;
    double peso;
    void (*imprimir)(Personagem);
    void (*ler)(char, Personagem);
} Personagem;

void ler(const char *fileData, Personagem *personagem)
{
    const char *temp = fileData;
    const char *value;

    // readNome
    temp = strstr(temp, "'name': '");
    if (temp != NULL)
    {
        temp += 9;
        value = temp;
        temp = strchr(temp, '\'');
        if (temp != NULL)
        {
            size_t len = temp - value;
            personagem->nome = (char *)malloc(len + 1);
            strncpy(personagem->nome, value, len);
            personagem->nome[len] = '\0';
        }
        // readAltura
        temp = strstr(temp, "'height': '");
        if (temp != NULL)
        {
            temp += 11;
            value = temp;
            temp = strchr(temp, '\'');
            if (temp != NULL)
            {
                size_t len = temp - value;
                char buf[len + 1];
                strncpy(buf, value, len);
                buf[len] = '\0';
                if (strcmp(buf, "unknown") == 0)
                {
                    personagem->altura = 0;
                }
                else
                {
                    personagem->altura = atoi(buf);
                }
            }
        }
        // readPeso
        temp = strstr(temp, "'mass': '");
        if (temp != NULL)
        {
            temp += 9;
            value = temp;
            temp = strchr(temp, '\'');
            if (temp != NULL)
            {
                size_t len = temp - value;
                char buf[len + 1];
                strncpy(buf, value, len);
                buf[len] = '\0';
                if (strcmp(buf, "unknown") == 0)
                {
                    personagem->peso = 0;
                }
                else
                {
                    personagem->peso = atof(buf);
                }
            }
        }
        // readCorDoCabelo
        temp = strstr(temp, "'hair_color': '");
        if (temp != NULL)
        {
            temp += 15;
            value = temp;
            temp = strchr(temp, '\'');
            if (temp != NULL)
            {
                size_t len = temp - value;
                personagem->corDoCabelo = (char *)malloc(len + 1);
                strncpy(personagem->corDoCabelo, value, len);
                personagem->corDoCabelo[len] = '\0';
            }
        }
        // readCodDaPele
        temp = strstr(temp, "'skin_color': '");
        if (temp != NULL)
        {
            temp += 15;
            value = temp;
            temp = strchr(temp, '\'');
            if (temp != NULL)
            {
                size_t len = temp - value;
                personagem->codDaPele = (char *)malloc(len + 1);
                strncpy(personagem->codDaPele, value, len);
                personagem->codDaPele[len] = '\0';
            }
        }
        // readCorDosOlhos
        temp = strstr(temp, "'birth_year': '");
        if (temp != NULL)
        {
            temp += 15;
            value = temp;
            temp = strchr(temp, '\'');
            if (temp != NULL)
            {
                size_t len = temp - value;
                personagem->corDosOlhos = (char *)malloc(len + 1);
                strncpy(personagem->corDosOlhos, value, len);
                personagem->corDosOlhos[len] = '\0';
            }
        }
        // readAnoNascimento
        temp = strstr(temp, "'gender': '");
        if (temp != NULL)
        {
            temp += 15;
            value = temp;
            temp = strchr(temp, '\'');
            if (temp != NULL)
            {
                size_t len = temp - value;
                personagem->anoNascimento = (char *)malloc(len + 1);
                strncpy(personagem->anoNascimento, value, len);
                personagem->anoNascimento[len] = '\0';
            }
        }
        // readGender
        temp = strstr(temp, "'homeworld': '");
        if (temp != NULL)
        {
            temp += 15;
            value = temp;
            temp = strchr(temp, '\'');
            if (temp != NULL)
            {
                size_t len = temp - value;
                personagem->genero = (char *)malloc(len + 1);
                strncpy(personagem->genero, value, len);
                personagem->genero[len] = '\0';
            }
        }
        // readHomeWorld
        temp = strstr(temp, "'films': '");
        if (temp != NULL)
        {
            temp += 15;
            value = temp;
            temp = strchr(temp, '\'');
            if (temp != NULL)
            {
                size_t len = temp - value;
                personagem->homeWorld = (char *)malloc(len + 1);
                strncpy(personagem->homeWorld, value, len);
                personagem->homeWorld[len] = '\0';
            }
        }
    }
}

void imprimir(Personagem personagem)
{
    printf("## %s", personagem->nome, " ## %d", personagem->altura, " ## %f", personagem->peso, " ## %s", personagem->corDoCabelo, " ## %s", personagem->codDaPele, " ## %s", personagem->corDosOlhos, " ## %s", personagem->anoNascimento, " ## %s", personagem->genero, " ## %s\n", personagem->homeWorld);
}

bool isFim(char *s)
{
    return strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M';
}

int main()
{

    char word[1000];
    scanf(" %[^\n]s", word);
    do
    {
        FILE *arquivo;
        char linha[100];

        arquivo = fopen(word, "r");

        if (arquivo == NULL)
        {
            printf("Erro ao abrir o arquivo.\n");
            return 1;
        }
        while (fgets(linha, sizeof(linha), arquivo) != NULL)
        {
            printf("%s", linha);
        }

        fclose(arquivo);
        scanf(" %[^\n]s", word);
    } while (!isFim(word));
    return 0;
}