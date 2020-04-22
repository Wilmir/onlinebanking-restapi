<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel = "stylesheet type = "text/css" href = "./css/index.css">
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <title> Online Banking Web Service</title>

</head>
    <body>
        <div class = "main">
            <div class = "entry">
                <div class = "widget login">            
                    <div class = "form" id = "login">
                        <div class = "title">Login</div>
                        <form>
                            <input type = "text" name="email" id="email" placeholder = "Email Address" required><br>
                            
                            <input type = "password" name="password" id="password" placeholder = "Password" required><br>
                            
                            <div class = "submit-btn">
                                <input type="submit" id = "submit" value="Log In">
                            </div>                    
                        </form>
                        <div class = "signupquestion">Not Registered? <span id = "signupoption">Sign Up</span></div>
                    </div>
                </div>
                <div id = "notice"></div>

            </div>
            
            <div class = "customer-container">
                <div class = "customer-header">

                </div>
                <div class = "customer-body">
                    <div class = "accounts-section">

                    </div>
                    <div class = "transactions-section">
                        
                    </div>
                </div>
            </div>  
        </div>    

        <div class = "modalSignUp">
            <div class = "modalSignUpContent">
                <div class = "widget signup">
                    <span class="closeSignUp">&times;</span>
                    <div class = "form">
                        <div class = "title">Sign Up</div>
                        <form method="POST" id = "signup">
                            <input type = text name="name" id="name" placeholder="Full Name" required><br>
                            
                            <input type = "text" name="email" id="email" placeholder = "Email Address" required><br>
                            
                            <input type = "password" name="password" id="password" placeholder ="Password" required><br>
                            
                            <input type = "text" name="address" id="address" placeholder = "Home Address" required><br>
                            <div class = "submit-btn">
                                <input type="submit" id = "submit" value="Register">
                            </div>
                        </form>
                    </div>
                </div>	
            </div>
        </div>

        <script src = "./js/register.js"></script>
        <script src = "./js/login.js"></script>
        <script src = "./js/loadCustomer.js"></script>
        <script src = "./js/addAccount.js"></script>
        <script src = "./js/signUpModal.js"></script>


    </body>
</html>