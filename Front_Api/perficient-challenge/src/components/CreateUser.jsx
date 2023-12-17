import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import { useEffect, useState } from 'react'
//import { Form, FormGroup, Button, Label, Input, FormText} from 'react-bootstrap'
import PropTypes from 'prop-types'
import axios from  '../config/axios'
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const CreateUser = () => {
    return(
        
            <div class="d-flex justify-content-end align-items-center vh-100">
                <div class="container col-4 ms-auto">
                    <div class="row">
                        <div class="col-12 tex-center">
                            <h1 class="display-1">Register</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="User name"/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="Password"/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="Nombre"/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="Apellido"/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="Pais"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-grid gap-2 mb-3">
                                <button type="button" class="btn btn-outline-primary btn-block rounded-pill">Crear</button>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        
    )
}

export default CreateUser