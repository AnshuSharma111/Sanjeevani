const express = require('express');

const router = express.Router();

// get hospital data api controller
const doctorController = require('../Controllers/doctor');

// set up routes
router.post('/leave/apply', doctorController.applyLeave);

// export router
module.exports = router;