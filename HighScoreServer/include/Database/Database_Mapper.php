<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 08.07.12
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */

require_once("./include/Database.php");
require_once("./Model/Model_Highscore.php");

abstract class Database_Mapper
{
    protected $_db;
    protected $_table;
    public function __construct(){
        $this->_db = new Database();
    }

    public function fetch( $rows, $order = NULL, $by = NULL, $limit = NULL){
        $tmpTable = explode("_",$this->_table);
        $table = new $this->_table();
        $model = $tmpTable[0] . "_" . $tmpTable[1];
        $result = $this->_db->fetch($model, $table->getTable(), $rows, $order,$by , $limit);
        $out = array();
        while($row = mysqli_fetch_array($result)) {
            $tmp = new $model();
            foreach($rows as $key ) {
                $method = "set" . $key;
                $tmp->$method($row[$key]);
            }
            $out[] = $tmp;
            unset($tmp);
        }
        return $out;
    }

    public function insert(array $rowsMap){
        $table = new $this->_table();
        $this->_db->insert($table->getTable(), $rowsMap);
    }
}
