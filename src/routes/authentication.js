const express = require('express');
const router = express.Router();

const passport = require('passport');
const { isLoggedIn, isNotLoggedIn } = require('../lib/auth');



// SIGNUP
router.get('/signup', isNotLoggedIn, (req, res) => {
    res.render('auth/signup');
});

router.post('/signup', passport.authenticate('local.signup', {
    successRedirect: '/perfil',
    failureRedirect: '/signup',
    failureFlash: true
}));



//SINGIN
router.get('/signin', isNotLoggedIn, (req, res) => {
    res.render('auth/signin');
});

router.post('/signin', (req, res, next) => {

    passport.authenticate('local.signin', {
        successRedirect: '/perfil',
        failureRedirect: '/signin',
        failureFlash: true
    })(req, res, next);
});


//SAIR
router.get('/sair', isLoggedIn, (req, res) => {
    req.logOut();
    res.redirect('/');
});


//PERFIL
router.get('/perfil', isLoggedIn, (req, res) => {
    res.render('perfil');
});


module.exports = router;
