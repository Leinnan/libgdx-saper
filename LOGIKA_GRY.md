## Klasa główna

Będzie ona zawierać główną logikę gry, a więc reagować na zmiany stanów w grze.


### Update
\\ (w Libgdx to co ja zawsze pakowałem osobno w metody render i update jest w jednej metodzie render)

Na początek sprawdź czy jest wczyśnięty przycisk
Jeśli jest zobacz czy w tym miejscu jest jakieś pole
Odsłoń je
Jesli to nie bomba to grasz dalej

## Board
<code>
Rectangle borders;

Field[][] Fields = new Fields(){][]

String[] fields_img_names = new String[11]- od 0 do 8 to grafiki przedstawiające odsloniete pola, następnie pole zasłonięte i bomba

Vector2 field_img_size;
</code>

Metody:

<code>
void calculateFieldNeighbors()
void generateBoard();
void generateBombs();
</code>

## Field

bool clicked
bool contain_bomb
int bomb_in_neighborhood
int index_x;
int index_y
Image image