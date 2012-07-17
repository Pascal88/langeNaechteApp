<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 09.07.12
 * Time: 00:00
 * To change this template use File | Settings | File Templates.
 */

require_once("./include/Database/Database_Table.php");

class Model_Highscore_DbTable extends Database_Table
{
    public function __construct(){
        $this->_table = "highscore";
    }
}
