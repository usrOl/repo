<?php

class EmployeeModel{
    private PDO $conn;
    
    public function __construct(){
        $database = new Database("localhost", "employees", "root", "");
        $this->conn = $database->getConnection();
    }

    public function CreateEmployee(array $data): bool{
        $sql = "CALL CreateEmployee(:p_name, :p_email, :p_position)";
        $stmt = $this->conn->prepare(($sql));

        $stmt->bindValue(':p_name', $data["name"], PDO::PARAM_STR);
        $stmt->bindValue(":p_email", $data["email"], PDO::PARAM_STR);
        $stmt->bindValue(":p_position", $data["position"], PDO::PARAM_STR);

        return $stmt->execute();
    }

    public function DeleteEmployee(int $id): bool{
        $sql = "CALL DeleteEmployee(:p_employee_id)";
        $stmt = $this->conn->prepare($sql);
        $stmt->bindValue(":p_employee_id", $id, PDO::PARAM_INT);
        
        return $stmt->execute();
        
        //return $stmt->rowCount();
    }

    public function GetEmployee($id): array{

        $sql = "CALL GetEmployee(:p_employee_id)";
        $stmt = $this->conn->prepare($sql);
        $stmt->bindValue(":p_employee_id", $id, PDO::PARAM_INT);
        $stmt->execute();
        
        $data = $stmt->fetch(PDO::FETCH_ASSOC);       
        if(!$data){
            return [];
        }
        return $data;
    }

    public function UpdateEmployee($data){
        $sql = "CALL UpdateEmployee(:p_employee_id, :p_name, :p_email, :p_position)";
        $stmt = $this->conn->prepare(($sql));

        $stmt->bindValue(":p_employee_id", $data["id"], PDO::PARAM_INT);
        $stmt->bindValue(':p_name', $data["name"], PDO::PARAM_STR);
        $stmt->bindValue(":p_email", $data["email"], PDO::PARAM_STR);
        $stmt->bindValue(":p_position", $data["position"], PDO::PARAM_STR);

        return $stmt->execute();
    }
}
?>