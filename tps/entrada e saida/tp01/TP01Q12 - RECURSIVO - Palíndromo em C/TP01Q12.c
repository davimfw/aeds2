#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isPalindromo(char *s, int position) {
    if(position < strlen(s)/2) {
        if(s[position] != s[strlen(s)-position-1]) {
            return false;
        } else {
            return true && isPalindromo(s, ++position);
        }
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
        printf(isPalindromo(word, 0) ? "SIM\n" : "NAO\n");
        scanf("%[^\n]", word);
    } while(isFim(word));
    return 0;
}