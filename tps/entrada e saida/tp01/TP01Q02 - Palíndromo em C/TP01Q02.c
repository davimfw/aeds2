#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isPalindromo(char *s) {
    for(int i = 0; i < strlen(s); i++) {
        if(s[i] != s[strlen(s)-i]) return false;
    }
    return true;
}

bool isFim(char *s) {
    return strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M';
}

int main() {
    char *word = "a";
    scanf("%[^\n]", word);
    do {
        printf(isPalindromo(word) ? "SIM\n" : "NAO\n");
        scanf("%[^\n]", word);
    } while(isFim(word));
    return 0;
}