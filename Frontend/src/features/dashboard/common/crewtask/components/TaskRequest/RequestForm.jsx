import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AiFillPlusSquare } from "react-icons/ai";
import { PiMinusSquareLight } from "react-icons/pi";
import { useForm } from "react-hook-form";
import useRequestData from "../../crewHooks/useRequestData";
import { CgSpinner } from "react-icons/cg";

const ReportRequestForm = () => {
  const {
    data,
    isLoading,
    errors,
    register,
    handleAddItem,
    handleMinus,
    handlePlus,
    handleSubmit,
    handleViewMaterial,
    onSubmit,
    materialList,
    activeRows,
    count,
  } = useRequestData();
  console.log("materials", data);
  return (
    <div className="min-w-full  p-6">
      <div className="bg-white min-h-screen rounded-[20px] px-5 pt-5 shadow-2xl">
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-9 ">
          <FormGroup
            label="Title"
            name="title"
            register={register}
            placeholder="Enter name"
          />
          <FormGroup
            label="Crew-ID"
            name="crewId"
            register={register}
            placeholder="Enter ID"
          />
          <FormGroup
            label="Description"
            name="description"
            register={register}
            placeholder="Enter description"
            isTextArea
          />
          <FormGroup
            label="Category"
            name="category"
            register={register}
            placeholder="Enter category"
          />

          <FormGroup
            label="Content"
            name="content"
            register={register}
            placeholder="Enter content"
          />
          <FormGroup
            label="Request Date"
            name="requestDate"
            register={register}
            type="date"
          />
          <div className="space-y-4">
            {/* Always visible label and button */}
            <div className="flex items-start gap-4">
              <label htmlFor="materialList" className="w-32 pt-2 font-semibold">
                Material List:
              </label>
              {!materialList && (
                <button
                  type="button"
                  onClick={handleViewMaterial}
                  className="bg-[#587C64] text-white px-4 py-2 rounded-lg"
                >
                  Add Material
                </button>
              )}
              {materialList && (
                <div className="shadow-lg bg-gray-50 w-1/2 p-2  ">
                  <table className=" px-4 overflow-hidden w-full">
                    <thead>
                      <tr className="">
                        <th className="font-semibold px-2 py-2 text-center border-r-2 border-r-gray-200 ">
                          Materials
                        </th>
                        <th className="font-semibold px-2 py-2 text-center border-r-2 border-r-gray-200 ">
                          Type
                        </th>
                        <th className="font-semibold px-2 py-2 text-center border-r-2 border-r-gray-200 ">
                          Available
                        </th>
                        <th className="font-semibold px-2 py-2 text-center ">
                          Quantity
                        </th>
                      </tr>
                    </thead>

                    <tbody>
                      {isLoading ? (
                        <tr>
                          <td
                            colSpan="6"
                            className="text-center py-4 text-gray-500 text-2xl"
                          >
                            <CgSpinner />
                          </td>
                        </tr>
                      ) : data?.contents?.length === 0 ? (
                        <tr>
                          <td
                            colSpan="6"
                            className="text-center py-4 text-gray-500 text-2xl"
                          >
                            <p>No Materials data.</p>
                          </td>
                        </tr>
                      ) : (
                        <>
                          {data?.contents?.map((item, i) => (
                            <tr key={i}>
                              <td className="font-semibold px-2 text-center py-2 border-r-2 border-r-gray-200">
                                {item.name}
                              </td>
                              <td className="font-semibold px-2 text-center py-2 border-r-2 border-r-gray-200">
                                {item.type}
                              </td>
                              <td className="font-semibold px-2 text-center py-2 border-r-2 border-r-gray-200">
                                {item.quantity}
                              </td>
                              <td className="font-semibold px-2 text-center py-2  ">
                                <button
                                  type="button"
                                  onClick={() => handleAddItem(i)}
                                  className="bg-[#587C64] text-white px-4 py-1 rounded-lg"
                                >
                                  {activeRows[i] ? (
                                    <div className="flex items-center gap-2">
                                      <button
                                        type="button"
                                        onClick={() => {
                                          handleMinus(i);
                                        }}
                                      >
                                        <PiMinusSquareLight size={24} />
                                      </button>
                                      <span>{count[i]}</span>
                                      <button
                                        type="button"
                                        onClick={() => {
                                          handlePlus(i);
                                        }}
                                      >
                                        <AiFillPlusSquare size={24} />
                                      </button>
                                    </div>
                                  ) : (
                                    "Add Item"
                                  )}
                                </button>
                              </td>
                            </tr>
                          ))}
                        </>
                      )}
                    </tbody>
                  </table>
                </div>
              )}
            </div>
          </div>
          <div className="flex justify-end space-x-4 ">
            <button
              type="submit"
              className="bg-[#587C64] shadow-lg text-white font-semibold px-6 py-2 rounded-md  hover:bg-blue-800 transition"
            >
              Request
            </button>
            <button
              type="button"
              className="border border-[#587C64] text-[#587C64] font-semibold px-6 py-2 rounded-md shadow-md hover:bg-blue-50 transition"
            >
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

const FormGroup = ({
  label,
  name,
  register,
  value,
  onChange,
  onClick,
  placeholder,
  isTextArea,
  isButton,
  type = "text",
}) => (
  <div className="flex items-start gap-4">
    <label htmlFor={name} className="w-32 pt-2 font-semibold">
      {label} :
    </label>
    {isTextArea ? (
      <textarea
        id={name}
        name={name}
        rows={4}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        {...register(name)}
        className="flex-1 border rounded-md px-4 py-2 outline-none focus:ring focus:ring-blue-200 resize-none"
      />
    ) : isButton ? (
      <button
        id={name}
        name={name}
        onClick={onClick}
        type="button"
        className="bg-[#587C64] text-white px-4 py-2 rounded-lg"
      >
        Add Material
      </button>
    ) : (
      <input
        id={name}
        name={name}
        type={type}
        placeholder={placeholder}
        {...register(name)}
        className="flex-1 border rounded-md px-4 py-2 outline-none focus:ring focus:ring-blue-200"
      />
    )}
  </div>
);

export default ReportRequestForm;
