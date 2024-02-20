<!--
  Matthew Yu, Adrian Ng
	2/26/2020
	The result page to start the quiz
	This file is used to view the results of the quiz.
-->
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Your quiz results</title>
    <link rel="stylesheet" href="resultsStyle.css">
  </head>
  <body>
    <?php

      session_start();

      // player answer
      $answer1 = $_POST['txtQuestion1'];
      $answer2 = $_POST['txtQuestion2'];
      $answer3 = $_POST['txtQuestion3'];
      $answer4 = $_POST['txtQuestion4'];
      $answer5 = $_POST['txtQuestion5'];
      $answer6 = $_POST['txtQuestion6'];
      $answer7 = $_POST['txtQuestion7'];
      $answer8 = $_POST['txtQuestion8'];
      $answer9 = $_POST['txtQuestion9'];
      $answer10 = $_POST['txtQuestion10'];

      $totalCorrect = 0;
      for ($i=1; $i <= 10; $i++) {
        eval('$systemAnswer = $_SESSION["question".$i."Answer"];');
        eval('$playerAnswer = $answer'.$i.';');

        // DEBUG: print(empty($playerAnswer) ? "<h1>it is empty</h1>" : "<h5>it is not empty</h5>");
        if (!is_null($playerAnswer) && !empty($playerAnswer)) {
          if ($systemAnswer == $playerAnswer) {
            $totalCorrect++;
          }
        }
      }

      date_default_timezone_set('America/New_York');
      $date = date("d/M/Y");

      //Record results
      if(file_exists("../data/userResults/".$_SESSION['username'].".txt") == false) {
        file_put_contents("../data/userResults/".$_SESSION['username'].".txt", $totalCorrect.",".$date."\r\n");
		  }	else{
			  file_put_contents("../data/userResults/".$_SESSION['username'].".txt", $totalCorrect.",".$date."\r\n", FILE_APPEND);
	    }
    ?>

    <h1><?php echo $_SESSION['username_'].", you got ".$totalCorrect." correct answers!"; ?></h1>
    <h2>Your results: </h2>
    <table class="resultTable">
      <!-- header -->
      <tr>
        <th style="color:LightCyan;">Number</th>
        <th style="color:LightSteelBlue;">Question</th>
        <th style="color:LightCyan;">Your Answer</th>
        <th style="color:LightSteelBlue;">Correct Answer</th>
        <th style="color:LightCyan;">Result</th>
      </tr>
      <!-- Question 1 -->
      <tr>
        <td><p class="number">1</p></td>
        <td><p class="question"><?php echo $_SESSION['question1'] ?></p></td>
        <td><p class="answer"><?php echo $answer1; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question1Answer'] ?></p></td>
        <td><p class=""><?php
          if (!is_null($answer1) && (!empty($answer1) || $answer1 == 0)) {
          if ($_SESSION['question1Answer'] == $answer1) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
      <!-- Question 2 -->
      <tr>
        <td><p class="number">2</p></td>
        <td><p class="question"><?php echo $_SESSION['question2'] ?></p></td>
        <td><p class="answer"><?php echo $answer2; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question2Answer'] ?></p></td>
        <td><p class=""><?php
        if (!is_null($answer2) && (!empty($answer2) || $answer2 == 0)) {
          if ($_SESSION['question2Answer'] == $answer2) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
      <!-- Question 3 -->
      <tr>
        <td><p class="number">3</p></td>
        <td><p class="question"><?php echo $_SESSION['question3'] ?></p></td>
        <td><p class="answer"><?php echo $answer3; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question3Answer'] ?></p></td>
        <td><p class=""><?php
        if (!is_null($answer3) && !empty($answer3)) {
          if ($_SESSION['question3Answer'] == $answer3) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
      <!-- Question 4 -->
      <tr>
        <td><p class="number">4</p></td>
        <td><p class="question"><?php echo $_SESSION['question4'] ?></p></td>
        <td><p class="answer"><?php echo $answer4; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question4Answer'] ?></p></td>
        <td><p class=""><?php
        if (!is_null($answer4) && !empty($answer4)) {
          if ($_SESSION['question4Answer'] == $answer4) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
      <!-- Question 5 -->
      <tr>
        <td><p class="number">5</p></td>
        <td><p class="question"><?php echo $_SESSION['question5'] ?></p></td>
        <td><p class="answer"><?php echo $answer5; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question5Answer'] ?></p></td>
        <td><p class=""><?php
        if (!is_null($answer5) && !empty($answer5)) {
          if ($_SESSION['question5Answer'] == $answer5) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
      <!-- Question 6 -->
      <tr>
        <td><p class="number">6</p></td>
        <td><p class="question"><?php echo $_SESSION['question6'] ?></p></td>
        <td><p class="answer"><?php echo $answer6; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question6Answer'] ?></p></td>
        <td><p class=""><?php
        if (!is_null($answer6) && !empty($answer6)) {
          if ($_SESSION['question6Answer'] == $answer6) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
      <!-- Question 7 -->
      <tr>
        <td><p class="number">7</p></td>
        <td><p class="question"><?php echo $_SESSION['question7'] ?></p></td>
        <td><p class="answer"><?php echo $answer7; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question7Answer'] ?></p></td>
        <td><p class=""><?php
        if (!is_null($answer7) && !empty($answer7)) {
          if ($_SESSION['question7Answer'] == $answer7) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
      <!-- Question 8 -->
      <tr>
        <td><p class="number">8</p></td>
        <td><p class="question"><?php echo $_SESSION['question8'] ?></p></td>
        <td><p class="answer"><?php echo $answer8; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question8Answer'] ?></p></td>
        <td><p class=""><?php
        if (!is_null($answer8) && !empty($answer8)) {
          if ($_SESSION['question8Answer'] == $answer8) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
      <!-- Question 9 -->
      <tr>
        <td><p class="number">9</p></td>
        <td><p class="question"><?php echo $_SESSION['question9'] ?></p></td>
        <td><p class="answer"><?php echo $answer9; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question9Answer'] ?></p></td>
        <td><p class=""><?php
        if (!is_null($answer9) && !empty($answer9)) {
          if ($_SESSION['question9Answer'] == $answer9) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
      <!-- Question 10 -->
      <tr>
        <td><p class="number">10</p></td>
        <td><p class="question"><?php echo $_SESSION['question10'] ?></p></td>
        <td><p class="answer"><?php echo $answer10; ?></p></td>
        <td><p class="QAnswer"><?php echo $_SESSION['question10Answer'] ?></p></td>
        <td><p class=""><?php
        if (!is_null($answer10) && !empty($answer10)) {
          if ($_SESSION['question10Answer'] == $answer10) {
            echo '<img src="../images/tick.png" width="50px" height="50px">';
          } else {
            echo '<img src="../images/cross.png" width="50px" height="50px">';
          }
        } else {
          echo '<img src="../images/cross.png" width="50px" height="50px">';
        }
        ?></p></td>
      </tr>
    </table>
    <br>
    <a href="../mainPage/verify.php">Back to home page</a>
  </body>
</html>
