const express = require('express');
const morgan = require('morgan');
const path = require('path');
const exphbs = require('express-handlebars');
const session = require('express-session');
const validator = require('express-validator');
const passport = require('passport');
const flash = require('connect-flash');
const MySQLStore = require('express-mysql-session')(session);
const bodyParser = require('body-parser');
const busboy = require("then-busboy");
const fileUpload = require('express-fileupload');

const { database } = require('./keys')

//inicializacoes
const app = express();
require('./lib/passport');


//definicoes
app.set('port', process.env.PORT || 4000);
app.set('views', path.join(__dirname, 'views'));
app.engine('.hbs', exphbs({
    defaultLayout: 'main',
    layoutsDir: path.join(app.get('views'), 'layouts'),
    partialsDir: path.join(app.get('views'), 'partials'),
    extname: '.hbs',
    helpers: require('./lib/handlebars')
}))
app.set('view engine', '.hbs');


//middlewares
app.use(session({
    secret: 'brakutxeu',
    resave: false,
    saveUninitialized: false,
    store: new MySQLStore(database)
}));

app.use(morgan('dev'));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(flash());
app.use(passport.initialize());
app.use(passport.session());
app.use(fileUpload());

//variaveis globais
app.use((req, res, next) => {
    app.locals.message = req.flash('message');
    app.locals.success = req.flash('success');
    app.locals.funcionario = req.user;
    next();
});

//routes
app.use(require('./routes/index'));
app.use(require('./routes/authentication'));
app.use('/home', require('./routes/home'));
app.use('/reservas', require('./routes/reservas'));
app.use('/hospedes', require('./routes/hospedes'));
app.use('/quartos', require('./routes/quartos'));
app.use('/pagamentos', require('./routes/pagamentos'));
app.use('/funcionarios', require('./routes/funcionarios'));
app.use('/limpeza', require('./routes/limpeza'));


//public
app.use(express.static(path.join(__dirname, 'public')));


//iniciar o servidor
app.listen(app.get('port'), () => {
    console.log(`Servidor na porta http://localhost:${app.get('port')}`);
})