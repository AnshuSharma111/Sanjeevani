const express = require('express');

const router = express.Router();

// get hospital data api controller
const locationController = require('../Services/location');

// set up routes
router.post('/hospitals', locationController.getHospitals);

// export router
module.exports = router;