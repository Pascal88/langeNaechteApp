<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 09.07.12
 * Time: 00:01
 * To change this template use File | Settings | File Templates.
 */
class Database_Table
{
    protected $_table;

    public function setTable($table)
    {
        $this->_table = $table;
    }

    public function getTable()
    {
        return $this->_table;
    }
}
