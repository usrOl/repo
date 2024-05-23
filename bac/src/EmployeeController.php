<?php
class EmployeeController{

    private EmployeeModel $model;
    public function __construct(){
        $this->model = new EmployeeModel();;
    }

    public function New(){
        //Ha a bodyban nincs semmi, és a json_decode NULL értéket ad vissza,
        //akkor egy üres tömbé lesz castolva
        $data = (array) json_decode(file_get_contents("php://input"));
        $errors = $this->getValidationErrors($data);
        if( ! empty($errors)){
            http_response_code(422);
            echo json_encode(["errors" => $errors]);
            return;
        }

        if($this->model->CreateEmployee($data)){
            echo json_encode([
                "message" => "Employee created"
            ]);
        }
    }
    public function Delete(){
        if(isset($_GET["id"])){
            $employee = $this->model->GetEmployee($_GET["id"]);
            $success = $this->model->DeleteEmployee($_GET["id"]);
            

            if($success && !empty($employee)){
                echo json_encode(["message" => "Successful delete"]);
                return;
            }
        }

        http_response_code(404);
        echo json_encode(["message" => "Employee not found"]);
    }
    public function Get(){
        if(isset($_GET["id"])){
            $employee = $this->model->GetEmployee($_GET["id"]);
            if(count($employee)){
                echo json_encode($employee);
                return;
            }
        }

        http_response_code(404);
        echo json_encode(["message" => "Employee not found"]);
        
    }
    public function Update(){
        $data = (array) json_decode(file_get_contents("php://input"));
        $errors = $this->getValidationErrors($data, true);
        if( ! empty($errors)){
            http_response_code(422);
            echo json_encode(["errors" => $errors]);
            return;
        }

        $employee = $this->model->GetEmployee($data["id"]);

        if(!empty($employee) && $this->model->UpdateEmployee($data)){
            echo json_encode([
                "message" => "Employee updated"
            ]);
            return;
        }

        http_response_code(404);
        echo json_encode(["message" => "Employee not found"]);
    }

    private function getValidationErrors(array $data, $is_update = false):array{
        $errors = [];
        
        if(empty($data["name"])){
            array_push($errors, "name is required");
        }
        if(empty($data["email"])){
            array_push($errors, "email is required");
        }
        if(empty($data["position"])){
            array_push($errors, "position is required");
        }
        if($is_update && empty($data["id"])){
            array_push($errors, "id is required");
        }

        return $errors;
    }

}
?>