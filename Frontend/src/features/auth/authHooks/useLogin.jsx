import React from 'react'
import { useAppStore } from '../../../stores/store';
import { useState } from 'react';
import { useNavigate
 } from 'react-router-dom';
 import { useForm } from 'react-hook-form';
 import {login} from "../../../services/auth"
import toast from 'react-hot-toast';
const useLogin = () => {
    const {setToken,setAccount}=useAppStore()
 const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const handleLogin =async (data) => {
    try{
        const res= await login(data);
        
        const json= await res.json();
        console.log("login data",json)
        const token =json.token;
        console.log("login token",token)
        setAccount(json.role,json.id);
        setToken(token)
        toast.success("Login Successfully!")
        if(json.role === "ROLE_CREW"){
            navigate("/dashboard/crew-task")
        }else if(json.role === "ROLE_LEADER"){
          navigate("/dashboard/leader-task")
        }
        else if(json.role === "ROLE_LEADER"){
          navigate("/dashboard/leader-task")
        }

    }catch(error){
        console.error("Error log in failed:",error);
    }
};

  return {handleLogin,register,handleSubmit,errors,showPassword,setShowPassword}
}

export default useLogin