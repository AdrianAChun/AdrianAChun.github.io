<!--
  Matthew Yu, Adrian Ng
	2/26/2020
	The main page to start the quiz
	This file is used to do the quiz, after player submited the quiz, it will go to results.php
-->
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Quiz</title>
    <link href='https://fonts.googleapis.com/css?family=Bungee' rel='stylesheet'> <!-- https://www.w3schools.com/howto/howto_google_fonts.asp -->
    <link href='https://fonts.googleapis.com/css?family=Frijole' rel='stylesheet'> <!-- https://www.w3schools.com/howto/howto_google_fonts.asp -->
    <link href='https://fonts.googleapis.com/css?family=Gaegu' rel='stylesheet'> <!-- https://www.w3schools.com/howto/howto_google_fonts.asp -->
    <script type="text/javascript" src="jquery.js"></script>
		<script type="text/javascript">
      "use strict";

      let timer = 0;

      $(document).ready(function($){
        // Update the count down every 1 second
        let x = setInterval(function() {

          timer++;

          document.getElementById("timer").innerHTML = "Time used: " + timer + "s";

        }, 1000);
      });
    </script>
    <link rel="stylesheet" href="quizStyle.css">
  </head>
  <body>
    <?php
      $question1Answer = $question2Answer = $question3Answer = $question4Answer = $question5Answer = $question6Answer = $question7Answer = $question8Answer = $question9Answer = $question10Answer = "";

      session_start();
      $_SESSION['question1'] = generateQuestions();
      $_SESSION['question2'] = generateQuestions();
      $_SESSION['question3'] = generateQuestions();
      $_SESSION['question4'] = generateQuestions();
      $_SESSION['question5'] = generateQuestions();
      $_SESSION['question6'] = generateQuestions();
      $_SESSION['question7'] = generateQuestions();
      $_SESSION['question8'] = generateQuestions();
      $_SESSION['question9'] = generateQuestions();
      $_SESSION['question10'] = generateQuestions();

      $_SESSION['username_'] = $_SESSION['username'];

      function calculate($equation) {
        eval('$result = (' .$equation. ');');
        return round($result);
      }

      $_SESSION['question1Answer'] = calculate($_SESSION['question1']);
      $_SESSION['question2Answer'] = calculate($_SESSION['question2']);
      $_SESSION['question3Answer'] = calculate($_SESSION['question3']);
      $_SESSION['question4Answer'] = calculate($_SESSION['question4']);
      $_SESSION['question5Answer'] = calculate($_SESSION['question5']);
      $_SESSION['question6Answer'] = calculate($_SESSION['question6']);
      $_SESSION['question7Answer'] = calculate($_SESSION['question7']);
      $_SESSION['question8Answer'] = calculate($_SESSION['question8']);
      $_SESSION['question9Answer'] = calculate($_SESSION['question9']);
      $_SESSION['question10Answer'] = calculate($_SESSION['question10']);


      function generateQuestions() {
        $question = "";

        $number = rand(2,4);
        /*
        * if generated 2, then it will be like X + x
        * if generated 3, then it will be like x + x + x
        * if generated 4, then it will be like x + x + x + x
        */

        //The do loop is to prevent the question has the answer of "0", so it should be better :D
        do {
          $question = "";
          for ($x=0; $x < $number ; $x++) {
            $randomNumber = rand(2, 20);

            if ($x != ($number - 1)) {
              $symbol = getSymbol(rand(1,4));
              $question = $question.$randomNumber.$symbol;
            } else {
              $question = $question.$randomNumber;
            }
          }
          $answer = calculate($question); //Have to make this first because "empty" cannot put function in it
        } while (empty($answer));

        return $question;
      }

      function getSymbol($data) {
        if ($data == 1) {
          return "+";
        } else if ($data == 2) {
          return "-";
        } else if ($data == 3) {
          return "*";
        } else if ($data == 4) {
          return "/";
        } else {
          error_log("Wried! Generated a number which is not 1 to 4.", 0);
        }
      }
    ?>

    <h2 class="timer" id="timer">Time used: 0s</h2>
    <h1>Math Quiz</h1>
    <h3>*NOTE: All answers should be rounded</h3>
    <br>
    <form action="results.php" method="post">
      <p class="question">Question 1: <?php echo $_SESSION['question1']; ?></p>
      <input type="text" name="txtQuestion1">
      <p class="question">Question 2: <?php echo $_SESSION['question2']; ?></p>
      <input type="text" name="txtQuestion2">
      <p class="question">Question 3: <?php echo $_SESSION['question3']; ?></p>
      <input type="text" name="txtQuestion3">
      <p class="question">Question 4: <?php echo $_SESSION['question4']; ?></p>
      <input type="text" name="txtQuestion4">
      <p class="question">Question 5: <?php echo $_SESSION['question5']; ?></p>
      <input type="text" name="txtQuestion5">
      <p class="question">Question 6: <?php echo $_SESSION['question6']; ?></p>
      <input type="text" name="txtQuestion6">
      <p class="question">Question 7: <?php echo $_SESSION['question7']; ?></p>
      <input type="text" name="txtQuestion7">
      <p class="question">Question 8: <?php echo $_SESSION['question8']; ?></p>
      <input type="text" name="txtQuestion8">
      <p class="question">Question 9: <?php echo $_SESSION['question9']; ?></p>
      <input type="text" name="txtQuestion9">
      <p class="question">Question 10: <?php echo $_SESSION['question10']; ?></p>
      <input type="text" name="txtQuestion10">

      <br><br>

      <input class="btnSubmit" type="submit" value="Submit Quiz!">
    </form>
  </body>
</html>
