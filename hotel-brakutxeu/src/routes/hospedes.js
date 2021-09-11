const express = require('express');
const router = express.Router();
const pool = require('../database');

const { isLoggedIn } = require('../lib/auth');


//localhost:400/hospedes
router.get('/', isLoggedIn, async (req, res) => {
     const hospedes = await pool.query('SELECT * FROM hospede');
     var title = 'Hóspedes';
     res.render('hospedes/list', { hospedes, title });
});

module.exports = router;