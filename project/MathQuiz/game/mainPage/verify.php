<!--
  Matthew Yu, Adrian Ng
	2/26/2020
	The check user page of the website
	This file is used to check if the username and password is in the database, and allow to start the quiz
-->
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Verify</title>
    <link href='https://fonts.googleapis.com/css?family=Annie Use Your Telescope' rel='stylesheet'><!-- https://www.w3schools.com/howto/howto_google_fonts.asp -->
    <link href='https://fonts.googleapis.com/css?family=Architects Daughter' rel='stylesheet'><!-- https://www.w3schools.com/howto/howto_google_fonts.asp -->
    <link href='https://fonts.googleapis.com/css?family=Arsenal' rel='stylesheet'><!-- https://www.w3schools.com/howto/howto_google_fonts.asp -->
    <style>
      h1{
        font-size: 6em;
        text-align: center;
        font-family: Annie Use Your Telescope;
        color: skyblue;
      }
      h2{
        font-size: 2em;
        text-align: center;
        color: orange;
        font-family: Architects Daughter;
      }
      h3{
        color: yellow;
        text-align: center;
        font-family: Arsenal;
        font-size: 1.7em;
      }
      body{
        background-attachment: fixed;
        /* background-repeat: no-repeat;
        background-size: cover; */
        background-image: linear-gradient(rgba(0, 0, 0, 0.8),rgba(0, 0, 0, 0.8)),url(https://images3.alphacoders.com/581/thumb-1920-581876.png); /*make background images darker with rgba*/
      }
      table{
        border-style: double;
        text-align: center;
        margin-left: auto;
        margin-right: auto;
        font-size: 2.5em;
      }
      th{
        border-style: solid;
      }
      td{
        font-size:0.9em;
      }
      a{
        border: solid;
        border-radius: 10px;
        background-color: rgb(107, 193, 250);
        /* extend the button size */
        padding-top: 10px;
        padding-bottom: 10px;
        /* center the button */
        display: block;
        text-align: center;
        margin-left: 700px;
        margin-right:700px;
        /* editing the text */
        font-size: 30px;
        color: white;
        transition: all 0.2s ease-in-out;
      }
      a:hover {
        color: rgba(255, 255, 255, 1);
        background-color: #2196f3;
        box-shadow: 0 5px 15px rgba(145, 92, 182, .4);
      }

      }
    </style>
  </head>
  <body>
    <?php

      //Get data from Form
      session_start();
      if (isset($_POST['txtUsername'])) {
        //This will run when that is set, since this var is only available in login page
        $username = $_POST['txtUsername']; //String
      } else {
        //This will run when that is set, only for results.php
        $username = $_SESSION['username_']; //String
      }
      $_SESSION['username'] = $username; //String
      if (isset($_POST['txtPassword'])) {
          $password = $_POST['txtPassword']; //String
      }

      //database but txt file LOL
      $fileData = file_get_contents("../data/loginData.txt");

      $lineInfo = explode("\r\n", $fileData); //Array with String

      $found = false; //This var will shows is user and password found or match the database
      for ($i=0; $i < count($lineInfo); $i++) {
        $dataLine = explode(",", $lineInfo[$i]); //Array split, now it will be an array which contains username and password.
        if ($dataLine == "\r\n") {
          break; //It is not able to split, so start another check
        }
        try {
          if ($dataLine[0] == $_SESSION['username'] && (isset($_POST['txtPassword']) ? ($dataLine[1] == $password) : true)) {
            $found = true;
            break; //Stop the loop to save performance.
          }
        } catch (Exception $e) {
          echo "An error has occured, please contact the website administor to fix it.";
        }
      }

      if ($found) {

        echo '<h1 class="txtWelcome">Welcome! '.$username.'</h1>';
        echo '<h2>Welcome to Math Quiz!!<br>
        Here is the ONLY reminder >.< <br>
        You have to finish the quiz as soon as possible with your highest score!!<br>
        Good Luck!!</h2>
 ';
        echo "<br>";
        echo '<h3>Last 10 results:</h3>';
        echo getPersonalResultTable();
        echo "<br><br>";
        echo '<a href="../quiz/quiz.php">Start the quiz</a>';

      } else {
        echo "<h1>Username or password incorrect!</h1>";
        echo '<h3 style="text-align: center;font-size:3em;"><a style="color:orange;" href="../index.html">Click me to back to login page!</a></h3>';
      }

      function getPersonalResultTable() {
        $table = "";

        if(file_exists("../data/userResults/".$_SESSION['username'].".txt") == false) {
          $table = '<p style="text-align: center; color: orange; font-size: 2em;">No data was found, try to play a game :D</p>';
        } else {
          $data = file_get_contents("../data/userResults/".$_SESSION['username'].".txt");
          $lineInfo = explode("\r\n", $data);
          $reversed = array_reverse($lineInfo);

          for ($i=0; $i < (count($reversed) < 11 ? count($reversed) : 11); $i++) {
            if (!isset($lineInfo[$i])) {
              break;
            }
            $splitedData = explode(",", $reversed[$i]);
            if (!isset($splitedData[1])) {
              continue;
            }
            $table = $table.
              '
              <tr>
                <td style="color:LightCyan;">'.($i).'</td>
                <td style="color:LightSteelBlue;">'.$splitedData[1].'</td>
                <td style="color:LightCyan;">'.$splitedData[0].'</td>
              </tr>
              ';
          }

          //Create Table
          $table =
              '<table>
                <tr>
                  <th style="color:LightCyan;">Last Tries</th>
                  <th style="color:LightSteelBlue;">Date</th>
                  <th style="color:LightCyan;">Results</th>
                </tr>
                '.$table.'
              </table>';
        }
        echo $table;
      }

    ?>
  </body>
</html>
