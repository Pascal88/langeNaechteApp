<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 09.07.12
 * Time: 00:00
 * To change this template use File | Settings | File Templates.
 */

require_once("./include/Database/Database_Mapper.php");
require_once("./Model/Highscore/Model_Highscore_DbTable.php");
require_once("./include/config.php");

class Model_Highscore_DbMapper extends Database_Mapper
{
    public function __construct(){
        $this->_table = "Model_Highscore_DbTable";
        parent::__construct();
    }

    public function setNewHighscore($username, $score){
        $this->insert(array("Username" => $username, "Score" => $score));
    }

    public function getSomeHighscores($number) {
        return $this->fetch(array("Username", "Score"), "DESC","Score" , $number);
    }

    public function toArray($array){
        $out = array();
        foreach($array as $value) {
            $out[] = array("Username" => $value->getUsername(), "Score" => $value->getScore());
        }
        return $out;
    }
}
