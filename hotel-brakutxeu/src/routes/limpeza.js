const express = require('express');
const router = express.Router();
const pool = require('../database');

const { isLoggedIn } = require('../lib/auth');


//localhost:400/funcionarios
router.get('/', isLoggedIn, async (req, res) => {
     const limpeza = await pool.query('SELECT * FROM `funcionario` WHERE cargo = ?', ['limpeza']);
     var title = 'Servicos de Limpeza'
     res.render('limpeza/list', { limpeza, title });
});

module.exports = router;