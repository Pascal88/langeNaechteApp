<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 05.07.12
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
include_once("config.php");

class Database
{
    private $_db;
    public function __construct(){
        $config = $_SESSION["config"];
        $this->_db = mysqli_connect(
                                    $config["db"]["url"],
                                    $config["db"]["user"],
                                    $config["db"]["password"],
                                    $config["db"]["database"])
            or die ('Keine Verbindung möglich');

    }

    public function fetch($model, $table, $rows, $order = NULL, $by = NULL , $limit = NULL) {
        $query = "";

        $query = "SELECT ";
        foreach($rows as $row) {
            $query = $query . $row . ", ";
        }

        $query = substr($query, 0, strlen($query)-2);

        $query = $query . " FROM " . $table;

        if(!is_null($order)) {
           $query = $query . " ORDER BY " . $by . " " . $order;
        }

        if (!is_null($limit)) {
            $query = $query . " LIMIT " . $limit;
        }

        $result = mysqli_query($this->_db, $query);

        if(!$result) {
         throw new Exception ("SELECT went wrong!");
        }

        return $result;
    }

    public function insert($table, $rowsMap) {
        $query = "INSERT INTO " . $table;
        $rows = " ( `";
        $values = " ( '";
        foreach ($rowsMap as $row => $value) {
            $rows = $rows . $row . "`, `";
            $values = $values .  $value . "', '";
        }
        $tmp = substr($rows, 0, strlen($rows)-3);
        $rows = $tmp;
        $rows = $rows . ")";

        $tmp = substr($values, 0, strlen($values)-3);
        $values = $tmp;
        $values = $values . ")";

        $query = $query . $rows . " VALUES " . $values;

        if(!mysqli_query($this->_db, $query)) {
            throw new Exception ("Insert went wrong!");
        }
    }

    public function __destruct(){
        mysqli_close($this->_db);
    }
}
