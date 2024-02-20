<!--
  Matthew Yu, Adrian Ng
	2/26/2020
	The register page of the quiz
	This file is used to give player to register an account
-->
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Register</title>

    <link href='https://fonts.googleapis.com/css?family=Alata' rel='stylesheet'> <!-- https://www.w3schools.com/howto/howto_google_fonts.asp -->
    <link href='https://fonts.googleapis.com/css?family=Archivo' rel='stylesheet'> <!-- https://www.w3schools.com/howto/howto_google_fonts.asp -->
    <link rel="stylesheet" href="registerStyle.css">

  </head>
  <body>
    <?php
      $requirement = true;
      $usernameError = $passwordError = $confirmPasswordError = "";
      //Get Data from Form
      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $username = $_POST['txtUsername'];
        $password = $_POST['txtCreatePassword'];
        $confirmPassword = $_POST['txtConfirmPassword'];

        //check if username is registered already
        $alreadyRegistered = false;
        $fileData = file_get_contents("../data/loginData.txt");
        $lineInfo = explode("\r\n", $fileData); //Array with String
        for ($i=0; $i < count($lineInfo); $i++) {
          $dataLine = explode(",", $lineInfo[$i]); //Array split, now it will be an array which contains username and password.
          if ($dataLine == "\r\n") {
            break; //It is not able to split, so start another check
          }
          try {
            if ($dataLine[0] == $username) {
              $alreadyRegistered = true;
              break; //Stop the loop to save performance.
            }
          } catch (Exception $e) {
            echo "An error has occured, please contact the website administor to fix it.";
          }
        }

        if (empty($username)) {
          $usernameError = "* Username is required";
          $requirement = false;
        } else if ($alreadyRegistered) {
          $usernameError = "* Username already used";
          $requirement = false;
        } else {
          $nameDummy = test_input($username);
          // check if name only contains letters and whitespace
          if (!preg_match("/^[a-zA-Z ]*$/", $nameDummy)) {
            $usernameError = "* Only letters and white space allowed";
            $requirement = false;
          }
        }

        if (empty($password)) {
          $passwordError = "* Password is required";
          $requirement = false;
        } else {
          $passwordDummy = test_input($password);
          // check if name only contains letters and whitespace
          if (!preg_match("/^[a-zA-Z ]*$/", $passwordDummy)) {
            $passwordError = "* Only letters and white space allowed";
            $requirement = false;
          }
        }

        if (empty($confirmPassword)) {
          $confirmPasswordError = "* Confirm password is required";
          $requirement = false;
        } else {
          $confirmPasswordDummy = test_input($confirmPassword);
          // check if name only contains letters and whitespace
          if (!preg_match("/^[a-zA-Z ]*$/", $confirmPasswordDummy)) {
            $confirmPasswordError = "* Only letters and white space allowed";
            $requirement = false;
          }
        }

        if ($requirement) {
          if ($password == $confirmPassword) {
            $contents = $username.",".$password."\r\n";
            file_put_contents("../data/loginData.txt", $contents, FILE_APPEND);
            echo '<h2 style="color:yellow; text-align: center;">Your information has been successfully registered! Thanks for visiting my site!</h2>';
            echo '<h3 style="text-align: center"><a style="color:orange;" href="../index.html">Click me to back to login page!</a></h3>';
          } else {
            $passwordError = "* The Password does not match!";
            $confirmPasswordError = "* The confirm Password confirmation does not match!";
          }
        }

        //Set back to true so if user correct it, it will able to register
        $requirement = true;
      }

      function test_input($data) {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
      }
    ?>
    <br>
    <h1>Sign Up Here</h1>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" method="POST">
      <table>
        <tr>
          <td><p class="register">Username:</p></td>
        </tr>
        <tr>
          <td><input class="txtInfo" type="text" name="txtUsername"></td>
        </tr>
        <tr>
          <td><span style="color: red;"><?php echo $usernameError;?></span></td>
        </tr>
        <tr>
          <td><p class="register">Create password:</p></td>
        </tr>
        <tr>
          <td><input  class="txtInfo" type="password" name="txtCreatePassword"></td>
        </tr>
        <tr>
          <td><span style="color: red;"><?php echo $passwordError;?></span></td>
        </tr>
        <tr>
          <td><p class="register">Confirm password:</p></td>
        </tr>
        <tr>
          <td><input class="txtInfo" type="password" name="txtConfirmPassword"></td>
        </tr>
        <tr>
          <td><span style="color: red;"><?php echo $confirmPasswordError;?></span></td>
        </tr>
        <tr>
          <td><br><br></td>
        </tr>
        <tr>
          <td><input class="btnRegister" type="submit" value="Register"></td>
        </tr>
        <tr>
          <td><br><br></td>
        </tr>
      </table>

        <br>
        <br>


    </form>
  </body>
</html>
