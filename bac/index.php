<?php

//class autoloader, NEM KÖTELEZŐ
spl_autoload_register(function($class){
    require( __DIR__ . "/src/$class.php");
});

//PHP Error handling, NEM KÖTELEZŐ
set_error_handler("ErrorHandler::handleError");
//Unexpected exception handling, NEM KÖTELEZŐ
set_exception_handler("ErrorHandler::handleException");

header("Content-type: application/json; charset=UTF-8");
header("Access-control-Allow-origin: *");
header("Access-control-Allow-Methods: GET, POST, PUT, PATCH, DELETE, OPTIONS");

$parts = explode("/", $_SERVER["REDIRECT_URL"]);

if ($parts[2] != "employees"){
    http_response_code(404);
    exit;
}

$endpoint = $parts[3] ?? null;

if ($parts[2] == "employees" ){
    $controller = new EmployeeController();

    if($endpoint == "new" && $_SERVER['REQUEST_METHOD'] === 'POST'){
        $controller->New();
    }else if($endpoint == "delete" && $_SERVER['REQUEST_METHOD'] === 'DELETE'){
        $controller->Delete();
    }else if($endpoint == "update" && $_SERVER['REQUEST_METHOD'] === 'PATCH'){
        $controller->Update();
    }else if($endpoint == "get" && $_SERVER['REQUEST_METHOD'] === 'GET'){
        $controller->Get();
    }else{
        http_response_code(405);
    }
}


?>