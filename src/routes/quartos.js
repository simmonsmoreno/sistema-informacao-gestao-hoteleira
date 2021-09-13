const express = require('express');
const router = express.Router();
const pool = require('../database');

const { isLoggedIn } = require('../lib/auth');


//localhost:400/quartos
router.get('/', isLoggedIn, async (req, res) => {
     const quartos = await pool.query('SELECT * FROM quarto');
     res.render('quartos/list', {quartos});
});

//localhost:400/quartos/add
router.get('/add', isLoggedIn, async (req, res) => {
     res.render('quartos/add');
});

router.post('/add', isLoggedIn, async (req, res) => {
     try {

          const { numero, preco, descricao, num_camas, tipo_quarto } = req.body;

          const quarto = {
               numero,
               preco,
               tipo: tipo_quarto,
               num_camas,
               descricao
          }

          const resultQuarto = await pool.query('INSERT INTO quarto SET ?', [quarto]);

          res.redirect('/quartos');

     } catch (error) {
          res.status(404).send(error);
     }
})

module.exports = router;