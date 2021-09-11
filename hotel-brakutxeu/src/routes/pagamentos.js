const express = require('express');
const router = express.Router();
const pool = require('../database');

const { isLoggedIn } = require('../lib/auth');


//localhost:400/hospedes
router.get('/', isLoggedIn, async (req, res) => {
     
     const pagamentos = await pool.query('SELECT * FROM ((((pagamento INNER JOIN hospedagem ON pagamento.hospedagem_numero = hospedagem.numero) INNER JOIN reserva ON hospedagem.reserva_codigo = reserva.codigo) INNER JOIN funcionario ON reserva.funcionario_id = funcionario.id) INNER join reserva_has_hospedes ON reserva_has_hospedes.reserva_codigo = reserva.codigo) inner join hospede on hospede.nif = reserva_has_hospedes.hospedes_nif');
     
     var title = 'Pagamentos';
     
     res.render('pagamentos/list', { pagamentos, title });
});

//localhost:400/hospedes
router.get('/ver/:codigo', isLoggedIn, async (req, res) => {
     
     const { codigo } = req.params;
     
     const pagamentos = await pool.query('SELECT * FROM ((((pagamento INNER JOIN hospedagem ON pagamento.hospedagem_numero = hospedagem.numero) INNER JOIN reserva ON hospedagem.reserva_codigo = reserva.codigo) INNER JOIN funcionario ON reserva.funcionario_id = funcionario.id) INNER join reserva_has_hospedes ON reserva_has_hospedes.reserva_codigo = reserva.codigo) inner join hospede on hospede.nif = reserva_has_hospedes.hospedes_nif WHERE num_pagamento = ?', [codigo]);

     var title = 'Pagamentos';

     res.render('pagamentos/ver', { pagamentos, title });
});

module.exports = router;