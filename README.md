### COMP90041 Project 2: Cribbage Game
This is the Project 2 for COMP90041(Programming and Software Development) from the University of Melbourne.

#### 1. The Game of Cribbage
Cribbage is a very old card game, dating to early 17th century England. The game can be played by two to six (and possibly more) players. The object of the game is to be the ﬁrst player to reach 121 points. Game play begins with the dealer dealing each player 4 to 6 cards (depending on how many are playing). Each player then selects four cards to keep and discards the rest. The cards discarded for all the players form an extra hand, called the _crib_ or _box_, which the dealer gets to count as a bonus. Next the player preceding the dealer cuts the deck to select an extra card, called the _start_ card.

The hand then proceeds to the _play_, which is outside the scope of this project, followed by the _show_, where each player counts the points in their hand according to the rules below, and adds the total to their running score. For this phase, the start card is usually considered as part of _each_ player’s hand, so each player establishes the value of a 5 card hand. Points are scored for certain combinations of cards according to the following rules:

* (**15s**) 2 points are scored for each distinct combinations of cards that add to 15. For this purpose, an ace is counted as 1, a jack, queen or king is counted as 10, and other cards are counted as their face value. For example, a hand with a 2, 3, 5, 10, and king scores 8 points: 2 points each for 2+3+10, 2+3+king, 5+10, and 5+king.

* (**Pairs**) 2 points are scored for each pair. With 3 of a kind, there are 3 diﬀerent ways to make a pair, so 3 of a kind scores 6 points. Similarly, 4 of a kind scores 12 points for the 6 possible pairs.

* (**Runs**) 1 point is scored for each card in a _run_ of 3 or more consecutive cards (the suits of these cards need not be the same). Aces are low in cribbage, so Ace, 2, 3 is a run, but Queen, King, Ace is not. For example, a 2, 6, 8, and 9, with a 7 as the start card scores 4 points for a 4 card run. It also scores a further 6 points for 15s (6+9, 7+8, and 2+6+7).

* (**Flushes**) 4 points is scored if all the cards in the hand are of the same suit. 1 further point is scored if the start card is also the same suit. Note that no points are scored if 3 of the hand cards, plus the start card, are the same suit.

* (**“One for his nob”**) 1 point is scored if the hand contains the jack of the same suit as the start card.

All of these points are totalled to ﬁnd the value of a hand. For example (using A for ace, T for 10, J for jack, Q for queen, and K for king, and ♣, ♦, ♥, and ♠ as clubs, diamonds, hearts, and spades):

<img src="https://github.com/Andy-TK/COMP90041_Project_Cribbage/blob/master/example.png" alt="example" width="70%">

Following the show, the cards are all collected and shuﬄed, and the person to the left of the dealer becomes the dealer for the next hand. The game continues this way until someone scores 121 points. Please see http://www.pagat.com/adders/crib6.html for a more complete description of the rules of cribbage. However, what is presented above will be enough to complete the project.

#### 2. What is the task?
For this project, you may select either of the below programs to develop. The ﬁrst is the easier project; students looking for a bigger challenge may want to select the second. **_Remember: you only need to do one of these._**

**Evaluate a hand**
> _You will write a Java main program that receives 5 cards on the command line and will print out only the number of points the hand comprising the ﬁrst four of those cards would score if the ﬁfth card were the start card. The table above shows some examples of inputs and the score your program should print._
>
> _For this project, you should submit a source ﬁle called `HandValue.java`, plus any other Java source ﬁles needed for the application. The correctness of your program will be assessed based on the correctness of the output produced for a number of input hands._

**Select a hand**
> _You will write a Java main program that receives 4–6 cards on the command line and will print out which four should be kept to maximise your chances of having a good hand once the start card is selected. Of course, if 4 cards are speciﬁed on the command line, there is only one choice of hand; if 5 are speciﬁed, there are 5 possible choices; if 6 are speciﬁed, there are 15 possibilities._
>
> _To make the best choice, you should consider each of the possible selections of four cards to keep (the others being discarded), and select the one with the greatest `expected score`. The expected score for a selection is the average hand value of the four cards to be kept taken with each of the possible start cards (every card in a full deck except the 4–6 speciﬁed on the command line, which cannot be the start card)._
>
> _For this project you should submit a source ﬁle called `SelectHand.java`, plus any other Java source ﬁles needed for the application. The correctness of your program will be assessed based on the optimality of the output produced for a number of input hands. That is, if the hand your program selects is almost as good as the optimal choice, you will receive most of the marks for that test._

For both programs, cards should be entered on the command line as two-character strings, the ﬁrst being an upper-case A for Ace, K for King, Q for Queen, J for Jack, T for Ten, or digit between 2 and 9 for ranks 2–9. The second character should be a C for Clubs (♣), D for Diamonds (♦), H for Hearts (♥), or a S for Spades (♠). For the SelectHand program, output should follow the same format, with selected cards separated by a single space. For both programs, the output should not contain any extra characters, except the single line should end with a newline character.

For more details, please check the [project specifications]().