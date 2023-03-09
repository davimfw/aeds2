#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool vogal(char *word)
{
    for (int i = 0; i < strlen(word); i++)
    {
        if (!(word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u'))
            return false;
    }

    return true;
}

bool consoante(char *word)
{
    if (vogal(word))
        return false;
    for (int i = 0; i < strlen(word); i++)
    {
        if (!(word[i] >= 'a' && word[i] <= 'z'))
            return false;
        if (word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u')
            return false;
    }
    return true;
}

bool isInteger(char *word)
{
    for (int i = 0; i < strlen(word); i++)
    {
        if (!(word[i] < '0' && word[i] > '9'))
            return false;
    }
    return true;
}

bool isDouble(char *word)
{
    int comma = 0;

    for (int i = 0; i < strlen(word); i++)
    {
        if (!(word[i] < '0' && word[i] > '9'))
        {
            if (word[i] == ',' || word[i] == '.')
            {
                comma++;
            }
            else
            {
                return false;
            }
        }
        if (comma > 1)
            return false;
    }

    return true;
}

char toLowerCase(char c)
{
    return (c >= 'a' && c <= 'z') ? ((char)(c + 32)) : c;
}

bool isFim(char *s)
{
    return strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M';
}

void is(char *word)
{
    for (int i = 0; i < strlen(word); i++)
    {
        word[i] = toLowerCase(word[i]);
    }
    printf(vogal(word) ? "SIM " : "NAO ");
    printf(consoante(word) ? "SIM " : "NAO ");
    printf(isInteger(word) ? "SIM " : "NAO ");
    printf(isDouble(word) ? "SIM\n" : "NAO\n");
}

int main()
{
    char word[1000];
    scanf(" %[^\n]s", word);
    do
    {
        is(word);
        scanf(" %[^\n]s", word);
    } while (!isFim(word));
    return 0;
}