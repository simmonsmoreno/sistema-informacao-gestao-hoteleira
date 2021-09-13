const express = require('express');
const router = express.Router();
const pool = require('../database');

const { isLoggedIn } = require('../lib/auth');


//localhost:400/home
router.get('/', isLoggedIn, async (req, res) => {
     res.render('home/list');
});

module.exports = router;