import React from 'react'
import Register from '../components/CreateUser'
import axios from  '../config/axios'
import { context } from '../context/context'
import { useEffect, useState } from 'react'
import store from "../store";

const register = () => {

    const[userRegister, setUserRegister] = useState({userName:"", password:"",firtsName:"",LastName:"", country:"",phone:""})

    const create = async (user) =>{
        const res = await axios.post("/login/register", user);
        console.log(res.data)
    }


    return(
        <context.Provider>
            <Register createUser={create}/>
        </context.Provider>
    )
}

export default register

