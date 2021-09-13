const express = require('express');
const router = express.Router();
const pool = require('../database');

const helpers = require('../lib/helpers');

const { isLoggedIn } = require('../lib/auth');


//localhost:400/funcionarios
router.get('/', isLoggedIn, async (req, res) => {
     const funcionarios = await pool.query('SELECT * FROM `funcionario` WHERE cargo != ?', ['administrador']);
     var title = 'Funcionários'
     res.render('funcionarios/list', { funcionarios, title });
});

//localhost:400/funcionarios/ver/codigo
router.get('/ver/:codigo', isLoggedIn, async (req, res) => {
     const { codigo } = req.params;
     const funcionario = await pool.query('SELECT * FROM funcionario WHERE id = ?', [codigo]);
     const reservas = await pool.query('SELECT * FROM (`reserva` INNER JOIN reserva_has_hospedes ON reserva_has_hospedes.reserva_codigo = reserva.codigo) INNER JOIN hospede ON hospede.nif = reserva_has_hospedes.hospedes_nif WHERE funcionario_id = ?', [codigo]);
     var title = 'Funcionários'
     res.render('funcionarios/ver', { funcionario, reservas, title});
});

//localhost:400/reservas/add
router.get('/add', isLoggedIn, (req, res) => {
     res.render('funcionarios/add');
})

//localhost:4000/funcionarios/add
router.post('/add', isLoggedIn, async (req, res) => {
     const { nome, senha, datanasc, cargo, salario, endereco, telefone, email } = req.body;
     
     let newFuncionario = {
          id: null,
          nome_f: nome,
          data_nasc: datanasc,
          cargo,
          senha,
          endereco,
          telefone,
          email,
          salario
     };

     //Atribuir o salario do novo funcionario de acordo com o cargo
     if (cargo == 'administrador') newFuncionario.salario = 250000;
     else if (cargo == 'recepcionista') newFuncionario.salario = 105000;
     else newFuncionario.salario = 45000

     //encriptar a senha do funcionario
     newFuncionario.senha = await helpers.encryptPassword(senha);

     // Salvar na base de dados
     await pool.query('INSERT INTO funcionario SET ? ', newFuncionario);

     res.redirect('/funcionarios');

})

//localhost:400/funcionarios/delete/id
router.get('/delete/:id', isLoggedIn, async (req, res) => {
     const { id } = req.params;
     await pool.query('DELETE FROM funcionario WHERE id = ?', [id]);
     req.flash('success', 'funcionario eliminado com sucesso!');
     res.redirect('/funcionarios');
});

//localhost:400/funcionarios/edit/id
router.get('/edit/:id', isLoggedIn, async (req, res) => {
     const { id } = req.params;
     const funcionario = await pool.query('SELECT * FROM funcionario WHERE id = ?', [id]);
     res.render('funcionarios/edit', { funcionario: funcionario[0] });
});

router.post('/edit/:id', isLoggedIn, async (req, res) => {
     const { id } = req.params;
     const { nome_f, data_nasc, cargo, senha, endereco, telefone, email, salario } = req.body;

     const updateFuncionario = {
          id,
          nome_f,
          data_nasc,
          cargo, senha,
          endereco,
          telefone,
          email,
          salario
     }

     await pool.query('UPDATE funcionario set ? WHERE id = ?', [updateFuncionario, id]);
     req.flash('success', 'funcionario atualizado com sucesso!');
     res.redirect('/funcionarios');
});

module.exports = router;