const express = require('express');
const router = express.Router();
const pool = require('../database');

const { isLoggedIn } = require('../lib/auth');


//localhost:400/reservas
router.get('/', isLoggedIn, async (req, res) => {
     const reservas = await pool.query('SELECT * FROM (`reserva` INNER JOIN reserva_has_hospedes ON reserva_has_hospedes.reserva_codigo = reserva.codigo) INNER JOIN hospede ON hospede.nif = reserva_has_hospedes.hospedes_nif');
     var title = 'Reservas';
     res.render('reservas/list', { reservas, title });
});

//localhost:400/reservas/ver/codigo
router.get('/ver/:codigo', isLoggedIn, async (req, res) => {
     const { codigo } = req.params;

     const reserva = await pool.query('SELECT * FROM (((((`reserva` INNER JOIN reserva_has_hospedes ON reserva_has_hospedes.reserva_codigo = reserva.codigo) INNER JOIN hospede ON hospede.nif = reserva_has_hospedes.hospedes_nif) INNER JOIN funcionario on reserva.funcionario_id = funcionario.id) INNER JOIN reserva_has_quarto on reserva.codigo = reserva_has_quarto.reserva_codigo) INNER JOIN quarto on quarto.numero = reserva_has_quarto.quarto_numero) INNER JOIN hospedagem ON hospedagem.reserva_codigo = reserva.codigo WHERE reserva.codigo = ?', [codigo]);

     var title = 'Reservas';
     res.render('reservas/ver', { reserva, title });
});

//localhost:400/reservas/add
router.get('/add', isLoggedIn, async (req, res) => {

     const quarto = await pool.query('SELECT * FROM quarto');
     res.render('reservas/add', { quarto });
})
router.post('/add', isLoggedIn, async (req, res) => {
     try {
          const { nome, nacionalidade, telefone, email, checkin, checkout, qtd_adultos, qtd_criancas, quarto } = req.body;


          const quartos = await pool.query('SELECT * FROM quarto WHERE numero = ?', [quarto]);

          quarto_preco = quartos[0].preco;
          

          //reserva
          const novaReserva = {
               codigo: null,
               status: 'solicitado',
               checkin,
               checkout,
               funcionario_id: req.user.id
          }
          const resultReserva = await pool.query('INSERT INTO reserva SET ?', [novaReserva]);


          //retirar a quantia total da reserva
          const result = await pool.query('SELECT ((checkout-checkin)*?) AS quantia FROM `reserva` WHERE codigo = ?', [quarto_preco, resultReserva.insertId]);


          //hospedagem
          const novahospedagem = {
               numero: null,
               quantia: result[0].quantia,
               num_adultos: qtd_adultos,
               num_criancas: qtd_criancas,
               reserva_codigo: resultReserva.insertId
          }
          await pool.query('INSERT INTO hospedagem SET ?', [novahospedagem]);


          //hospedes
          const novohospedes = {
               nif: null,
               nome_h: nome,
               nacionalidade,
               telefone,
               email
          }
          const resultHospede = await pool.query('INSERT INTO hospede SET ?', [novohospedes]);


          //reserva_has_hospedes
          const reserva_has_hospedes = {
               reserva_codigo: resultReserva.insertId,
               hospedes_nif: resultHospede.insertId
          }
          await pool.query('INSERT INTO reserva_has_hospedes SET ? ', [reserva_has_hospedes]);


          //reserva_has_hospedes
          const reserva_has_quarto = {
               reserva_codigo: resultReserva.insertId,
               quarto_numero: quarto
          }
          await pool.query('INSERT INTO reserva_has_quarto SET ? ', [reserva_has_quarto]);

          req.flash('success', 'Reserva feita com sucesso!');
          res.redirect('/reservas');

     } catch (error) {
          res.status(404).send(error);
          console.log(error);
     }
})


//localhost:400/reservas/delete/codigo
router.get('/delete/:codigo', isLoggedIn, async (req, res) => {
     const { codigo } = req.params;
     await pool.query('DELETE FROM reserva WHERE codigo = ?', [codigo]);
     req.flash('success', 'Reserva eliminada com sucesso!');
     res.redirect('/reservas');
});


//localhost:400/reservas/edit/codigo
router.get('/edit/:codigo', isLoggedIn, async (req, res) => {

     const { codigo } = req.params;

     const reserva = await pool.query('SELECT * FROM reserva WHERE codigo = ?', [codigo]);

     if(reserva[0].status = 'solicitado') {
          var nextstatus = 'confirmado';
     }else{

          console.log(reserva[0].status);
          if(reserva[0].status = 'confirmado'){
               var nextstatus = 'hospedado';
          }else{
               if(reserva[0].status = 'hospedado'){
                    var nextstatus = 'finalizado';
               }
          }
     }

     await pool.query('UPDATE reserva set status=? WHERE codigo = ?', [nextstatus, codigo]);

     req.flash('success', 'Reserva atualizada com sucesso!');
     res.redirect('/reservas');
});



//localhost:400/reservas/status
router.get('/solicitado', isLoggedIn, async (req, res) => {
     const reservas = await pool.query('SELECT * FROM reserva WHERE status = ?', ['solicitado']);
     var title = 'Reservas Solicitadas';
     res.render('reservas/list', { reservas, title });
});


//localhost:400/reservas/status
router.get('/confirmado', isLoggedIn, async (req, res) => {
     const reservas = await pool.query('SELECT * FROM reserva WHERE status = ?', ['confirmado']);
     var title = 'Reservas Confirmadas';
     res.render('reservas/list', { reservas, title });
});


//localhost:400/reservas/status
router.get('/hospedado', isLoggedIn, async (req, res) => {
     const reservas = await pool.query('SELECT * FROM reserva WHERE status = ?', ['hospedado']);
     var title = 'Reservas Hospedadas';
     res.render('reservas/list', { reservas, title });
});


//localhost:400/reservas/status
router.get('/cancelado', isLoggedIn, async (req, res) => {
     const reservas = await pool.query('SELECT * FROM reserva WHERE status = ?', ['cancelado']);
     var title = 'Reservas Canceladas';
     res.render('reservas/list', { reservas, title });
});


//localhost:400/reservas/status
router.get('/finalizado', isLoggedIn, async (req, res) => {
     const reservas = await pool.query('SELECT * FROM reserva WHERE status = ?', ['finalizado']);
     var title = 'Reservas Finalizadas';
     res.render('reservas/list', { reservas, title });
});

module.exports = router;