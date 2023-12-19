import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import { useEffect, useState } from 'react'
//import { Form, FormGroup, Button, Label, Input, FormText} from 'react-bootstrap'
import PropTypes from 'prop-types'
import axios from  '../config/axios'
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import background from "../img/login.jpeg";
import login from '../pages/login';
import {useGlobalState, setGlobalState} from '../index'



const authentication = ({addUser, userEdit}) => {

    const[token, setToken] = useState('')
    const[name, setUserName] = useState('')
    const[password, setPassword] = useState('')

    let navigate = useNavigate();

    useEffect(()=>{
       setToken(userEdit.token) 
    },[userEdit])

    const handleClick = () => {
        axios.post("/login/create", {name,password}).then
        (res =>{
            if(res.status === 200){
                localStorage.setItem("token", res.data.token);
                navigate('/app');
            }
        })
        setToken(userEdit.token)
        console.log(userEdit)
        localStorage.setItem("token", token);
        navigate('/app');
    }

    const handleRute = () =>{
        navigate('/auth');
    }


    return(
        <>
            <div class="d-flex justify-content-center align-items-center vh-100" style={{ backgroundImage: `url(${background})`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat', backgroundPosition: 'center center'}}>
                <div class="container col-3" style={{ backgroundColor: 'white' }}>
                    <div class="row">
                        <div class="col-12 text-center">
                            <h1 class="display-1">Login</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="User name" onChange={(e)=>{setUserName(e.target.value),setGlobalState("name",e.target.value)}}/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="password" class="form-control form-control rounded-pill" placeholder="Password" onChange={(e)=>{setPassword(e.target.value)}}/>
                            </div>
                            
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-grid gap-2 mb-3">
                                    <button type="button" class="btn btn-outline-primary btn-block rounded-pill" onClick={handleClick}>Login</button>
                                </div>
                                <div class="d-grid gap-2 mb-3">
                                    
                                        <button type="button" class="btn btn-outline-secondary btn-block rounded-pill" onClick={handleRute}>Register</button>
                                    
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

authentication.propTypes = {
    addUser: PropTypes.func,
    userEdit: PropTypes.object
}

export default authentication