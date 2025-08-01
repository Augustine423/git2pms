import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { IoMdEye, IoMdEyeOff } from "react-icons/io";
import footer from "../../assets/common/footer.png"; // Update if needed
import { useAppStore } from "../../stores/store";
import useLogin from "./authHooks/useLogin";
const LoginForm = () => {
const{handleLogin,register,handleSubmit,errors,showPassword,setShowPassword}=useLogin();
  return (
    <div className="w-screen h-screen flex justify-center items-center bg-gradient-to-r from-slate-300 to-blue-800 font-sans">
      <form
        onSubmit={handleSubmit(handleLogin)}
        className="w-full max-w-md p-10 rounded-xl bg-white bg-opacity-10 backdrop-blur-md text-white shadow-xl"
      >
        <div className="text-center mb-10">
          <img
            src={footer}
            alt="MarineDock"
            className="mx-auto w-15 h-20 mb-4"
          />
          <h1 className="text-4xl font-bold">Welcome</h1>
          <p className="text-sm mt-2">Log In To Your Account</p>
        </div>

       {/* {} Email */} 
        <div className="mb-6">
          <label className="block mb-1">Email</label>
          <input
            type="username"
            {...register("username", { required: "Email is required" })}
            placeholder="Enter Your Email"
            className="w-full p-3 rounded-md bg-white bg-opacity-20 text-white placeholder-white border border-white"
          />
          {errors.username && (
            <p className="text-red-300 text-sm mt-1">{errors.username.message}</p>
          )}
        </div>

        {/* Password */}
       <div className="mb-6 relative">
          <label className="block mb-1">Password</label>
          <input
            type={showPassword ? "text" : "password"}
            {...register("password", { required: "Password is required" })}
            placeholder="Enter Your Password"
            className="w-full p-3 rounded-md bg-white bg-opacity-20 text-white placeholder-white border border-white"
          />
          <span
            className="absolute right-4 top-10 cursor-pointer"
            onClick={() => setShowPassword((prev) => !prev)}
          >
            {showPassword ? <IoMdEyeOff /> : <IoMdEye />}
          </span>
          {errors.password && (
            <p className="text-red-300 text-sm mt-1">
              {errors.password.message}
            </p>
          )}
        </div>

        {/* Role Dropdown */}
        {/* <div className="mb-6">
          <label className="block mb-1">Select Role</label>
          <select 
            {...register("role", { required: "Role is required" })}
            className="w-full p-3 rounded-md bg-white bg-opacity-20 text-white border border-white"
            defaultValue=""
          >
            <option value=""disabled>
              Select your role
            </option>
            <option className="text-black" value="captain">
              Captain
            </option>
            <option className="text-black" value="crew">
              Crew
            </option>
          </select>
          {errors.role && (
            <p className="text-red-300 text-sm mt-1">{errors.role.message}</p>
          )}
        </div> */}

        {/* Login Button */}
                <button
          type="submit"
          className="w-full py-3 rounded-md bg-white text-blue-600 font-bold hover:bg-opacity-80 transition"
        >
          Log In
        </button>
      </form>
    </div>
  );
};

export default LoginForm;
