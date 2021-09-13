const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;

const pool = require('../database');
const helpers = require('./helpers');

//login
passport.use('local.signin', new LocalStrategy({
  usernameField: 'nome',
  passwordField: 'senha',
  passReqToCallback: true
}, async (req, nome, senha, done) => {
  
  const rows = await pool.query('SELECT * FROM funcionario WHERE nome_f = ?', [nome]);
  console.log(rows[0]);

  if (rows.length > 0) {

    const funcionario = rows[0];

    if (helpers.matchPassword(senha, funcionario.senha)) {
      done(null, funcionario, req.flash('success', 'Bem-Vindo ' + funcionario.nome_f));
    } else {
      done(null, false, req.flash('message', 'Senha incorreta'));
    }

  } else {
    return done(null, false, req.flash('message', 'Funcionário inexistente'));
  }
}));


//registrar
passport.use('local.signup', new LocalStrategy({
  usernameField: 'nome',
  passwordField: 'senha',
  passReqToCallback: true
}, async (req, nome, senha, done) => {

  const { datanasc, cargo, salario, endereco, telefone, email } = req.body;
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
  const result = await pool.query('INSERT INTO funcionario SET ? ', newFuncionario);
  newFuncionario.id = result.insertId;
  return done(null, newFuncionario);
}));

passport.serializeUser((funcionario, done) => {
  done(null, funcionario.id);
});

passport.deserializeUser(async (id, done) => {
  const rows = await pool.query('SELECT * FROM funcionario WHERE id = ?', [id]);
  done(null, rows[0]);
});