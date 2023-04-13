#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <assert.h>
#include <time.h>
#include <math.h>
#define MAX_CHARS 29
static int comparisons = 0;
static int moves = 0;

// ==================================== Definitions ================================== //

#define N_JSONS 86

typedef struct SWCharacter_s SWCharacter;

// ==================================== Prototypes ================================== //

size_t str_len(const char *const str);
short str_equals(const char *const str, const char *const other);
char *str_substring(const char *const str, const size_t start, const size_t end);
char *str_clone(const char *const str);
void str_remove_all(char *const str, const char c);
void file_to_string(const char *const path, char *const str);
char *json_substring(const char *const str, const char *const key, size_t *const index);
int parse_int(const char *const str);
double parse_double(const char *str);
short isFim(const char *const str);

SWCharacter SWCharacter_new();
SWCharacter SWCharacter_from_file(const char *const path);
SWCharacter swc_clone(const SWCharacter *const self);
void swc_print(const SWCharacter *const self);
void swc_free(SWCharacter *const self, const size_t len);

// ==================================== Utils ================================== //

size_t str_len(const char *const str)
{
    size_t len = 0;

    while (str[len])
        len++;

    return len;
}

short str_equals(const char *const str, const char *const other)
{
    size_t i = 0;
    short equals = 1;

    while (equals && str[i] && other[i])
    {
        equals = str[i] == other[i];
        i++;
    }

    return equals && !(str[i] || other[i]);
}

char *str_substring(const char *const str, const size_t start, const size_t end)
{
    size_t i = 0;
    char *substr = (char *)malloc((end - start + 1) * sizeof(char));

    while (i < end - start)
    {
        substr[i] = str[start + i];
        substr[++i] = '\0';
    }

    return substr;
}

char *str_clone(const char *const str)
{
    size_t len = str_len(str) + 1;
    char *copy = (char *)malloc(len * sizeof(char));
    for (size_t i = 0; i < len; i++)
        copy[i] = str[i];
    return copy;
}

void str_remove_all(char *const str, const char c)
{
    size_t len = str_len(str);
    size_t i = 0;

    while (i < len && str[i] != c)
        i++;

    for (size_t j = i; j < len; i++, j++)
    {
        if (str[i] == c)
            j++;
        str[i] = str[j];
    }

    str[i] = '\0';
}

void file_to_string(const char *const path, char *const str)
{
    FILE *f = fopen(path, "r");
    fscanf(f, " %1000[^\n]", str);
    fclose(f);
}

char *json_substring(const char *const str, const char *const key, size_t *const index)
{
    size_t start = *index + str_len(key) + 4;
    *index = start;
    while (str[++*index] != '\'')
        ;
    *index += 4;
    return str_substring(str, start, *index - 4);
}

int parse_int(const char *const str)
{
    int a = 0;
    int b = 1;

    for (int i = str_len(str) - 1; i >= 0; i--, b *= 10)
        a += (str[i] - 48) * b;

    return a;
}

double parse_double(const char *str)
{
    double value = 0;
    int dot = 0;
    double scale = 1;
    int negative = 0;

    if (*str == '-')
    {
        str++;
        negative = 1;
    }

    while (*str)
    {
        if (dot)
        {
            scale = scale / 10;
            value = value + (*str - 48) * scale;
        }
        else
        {
            if (*str == '.')
                dot++;
            else
                value = value * 10.0 + (*str - 48);
        }

        str++;
    }

    return negative ? -value : value;
}

short isFim(const char *const str)
{
    return str_equals(str, "FIM");
}

// ==================================== Structs ================================== //

struct SWCharacter_s
{
    char *name;
    int height;
    double mass;
    char *hair_color;
    char *skin_color;
    char *eye_color;
    char *birth_year;
    char *gender;
    char *homeworld;
};

// ==================================== Constructors ================================== //

SWCharacter SWCharacter_new()
{
    SWCharacter self;

    self.name = NULL;
    self.height = 0;
    self.mass = 0;
    self.hair_color = NULL;
    self.skin_color = NULL;
    self.eye_color = NULL;
    self.birth_year = NULL;
    self.gender = NULL;
    self.homeworld = NULL;

    return self;
}

SWCharacter SWCharacter_from_file(const char *const path)
{
    char *json = (char *)malloc(1000 * sizeof(char));
    SWCharacter self;
    char *str = NULL;
    size_t index = 2;

    file_to_string(path, json);

    self.name = json_substring(json, "name", &index);

    str = json_substring(json, "height", &index);
    self.height = str_equals(str, "unknown") ? 0 : parse_int(str);
    free(str);

    str = json_substring(json, "mass", &index);
    str_remove_all(str, ',');
    self.mass = str_equals(str, "unknown") ? 0 : parse_double(str);
    free(str);

    self.hair_color = json_substring(json, "hair_color", &index);
    self.skin_color = json_substring(json, "skin_color", &index);
    self.eye_color = json_substring(json, "eye_color", &index);
    self.birth_year = json_substring(json, "birth_year", &index);
    self.gender = json_substring(json, "gender", &index);
    self.homeworld = json_substring(json, "homeworld", &index);

    free(json);

    return self;
}

// ==================================== SWCharacter Methods ================================== //

SWCharacter swc_clone(const SWCharacter *const self)
{
    SWCharacter other;

    other.name = str_clone(self->name);
    other.height = self->height;
    other.mass = self->mass;
    other.hair_color = str_clone(self->hair_color);
    other.skin_color = str_clone(self->skin_color);
    other.eye_color = str_clone(self->eye_color);
    other.birth_year = str_clone(self->birth_year);
    other.gender = str_clone(self->gender);
    other.homeworld = str_clone(self->homeworld);

    return other;
}

void swc_print(const SWCharacter *const self)
{
    printf(" ## ");
    printf("%s ## ", self->name);
    printf("%d ## ", self->height);

    if (self->mass == (int)self->mass)
    {
        printf("%d ## ", (int)self->mass);
    }
    else
    {
        printf("%.1lf ## ", self->mass);
    }

    printf("%s ## ", self->hair_color);
    printf("%s ## ", self->skin_color);
    printf("%s ## ", self->eye_color);
    printf("%s ## ", self->birth_year);
    printf("%s ## ", self->gender);
    printf("%s ## ", self->homeworld);
    printf("\n");
}

void swc_free(SWCharacter *const self, const size_t len)
{
    if (self)
    {
        for (size_t i = 0; i < len; i++)
        {
            if (self[i].name)
                free(self[i].name);
            if (self[i].hair_color)
                free(self[i].hair_color);
            if (self[i].skin_color)
                free(self[i].skin_color);
            if (self[i].eye_color)
                free(self[i].eye_color);
            if (self[i].gender)
                free(self[i].gender);
            if (self[i].birth_year)
                free(self[i].birth_year);
            if (self[i].homeworld)
                free(self[i].homeworld);
        }
        free(self);
    }
}

void createLog(clock_t inicio, clock_t fim)
{
    FILE *arquivo = fopen("matricula_binaria.txt", "w");

    double resultado = ((fim - inicio) / (double)CLOCKS_PER_SEC);

    fprintf(arquivo, "Matricula: 749605\tTempo: %lfs\tComparacoes: %d", resultado, comparisons);

    fclose(arquivo);
}

void sortBySelectionRecursive(SWCharacter *p, int n, int i)
{
    if (i >= n - 1)
    {
        return;
    }

    int menor = i;

    for (int j = (i + 1); j < n; j++)
    {
        if (strcmp(p[menor].name, p[j].name) > 0)
        {
            menor = j;
        }
    }

    // Trocar posicao de lugar

    SWCharacter *temp = (SWCharacter *)malloc(1 * sizeof(SWCharacter));
    temp[0] = p[i];
    p[i] = p[menor];
    p[menor] = temp[0];

    // Fazer a chamada recursiva
    sortBySelectionRecursive(p, n, i + 1);

    // Contar o numero de movimentacoes e comparacoes
    int comparasionsSelection = 0;
    moves = 3 * (n - 1);
    comparasionsSelection = (int)(pow((n - 1), 2) + (n - 1)) / 2;
    comparisons = comparisons + comparasionsSelection;
}

bool pesquisaBinaria(SWCharacter *p, char *procurar)
{
    bool resp = false;
    int direita = MAX_CHARS - 1;
    int esquerda = 0;
    int meio = 0;

    while (esquerda <= direita)
    {
        meio = (esquerda + direita) / 2;

        if (strcmp(procurar, p[meio].name) >= 0)
        {
            esquerda = meio + 1;
        }
        else
        {
            direita = meio - 1;
        }
        comparisons = comparisons + 2; // Contando comparacoes
    }

    comparisons++;

    if (direita <= MAX_CHARS - 1 && direita >= 0)
    {
        comparisons = comparisons + 3; // Contando comparacoes
        if (strcmp(p[direita].name, procurar) == 0)
        { // Contando comparacoes
            resp = true;
            return resp;
        }
    }
    // Assumindo a saida do if no pior caso
    comparisons = comparisons + 2;

    return resp;
}

// ==================================== Main ================================== //

int main()
{
    clock_t inicio, fim;
    inicio = clock();
    SWCharacter *array = (SWCharacter *)malloc(N_JSONS * sizeof(SWCharacter));
    size_t len = 0;
    char *str = (char *)malloc(100 * sizeof(char));
    scanf(" %100[^\n]", str);

    while (!isFim(str))
    {
        array[len++] = SWCharacter_from_file(str);
        scanf(" %100[^\n]", str);
    }

    // Lendo segunda parte
    int lenNomes = -1;
    char strNomes[26][30];

    do
    {
        lenNomes++;
        scanf("%s", strNomes[lenNomes]);
    } while (!isFim(strNomes[lenNomes]));

    sortBySelectionRecursive(array, len, 0);

    for (int i = 0; i < lenNomes; i++)
    {
        if (pesquisaBinaria(array, strNomes[i]) == true)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
    }

    fim = clock();
    createLog(inicio, fim);

    swc_free(array, len);
    free(str);
    return 0;
}