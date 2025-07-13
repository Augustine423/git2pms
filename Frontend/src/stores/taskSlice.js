// src/features/task/taskSlice.js
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import toast from "react-hot-toast";

export const taskUrl = `${import.meta.env.VITE_API_URL}/operations/tasks/api`;

/* -------------------------------------------------------------------------- */
/*                               Thunk actions                                */
/* -------------------------------------------------------------------------- */

// 1️⃣ Fetch paginated tasks
export const fetchTasks = createAsyncThunk(
  "tasks/fetchTasks",
  async ({ page = 0, size = 10 } = {}, { rejectWithValue }) => {
    try {
      const res = await axios.get(taskUrl, { params: { page, size } });
      return res.data;                // → { contents, totalPages, currentPage }
    } catch (err) {
      return rejectWithValue(err.response?.data || err.message);
    }
  }
);

// 2️⃣ Fetch task list for print
export const fetchTaskPrint = createAsyncThunk(
  "tasks/fetchTaskPrint",
  async (_, { rejectWithValue }) => {
    try {
      const res = await axios.get(`${taskUrl}/print`);
      return res.data;
    } catch (err) {
      return rejectWithValue(err.response?.data || "Failed to fetch tasks");
    }
  }
);

// 3️⃣ Fetch single task (detail)
export const fetchTaskById = createAsyncThunk(
  "tasks/fetchTaskById",
  async (id, { rejectWithValue }) => {
    try {
      const res = await axios.get(`${taskUrl}/${id}`);
      return res.data;
    } catch (err) {
      return rejectWithValue(err.response?.data || "Failed to fetch task");
    }
  }
);

// 4️⃣ Create
export const addTask = createAsyncThunk(
  "tasks/addTask",
  async (taskData, { rejectWithValue }) => {
    try {
      const res = await axios.post(taskUrl, taskData);
      toast.success("Task created");
      return res.data;                // returns created task object
    } catch (err) {
      toast.error(err.response?.data?.message || "Create failed");
      return rejectWithValue(err.response?.data || err.message);
    }
  }
);

// 5️⃣ Update
export const updateTask = createAsyncThunk(
  "tasks/updateTask",
  async ({ id, taskData }, { rejectWithValue }) => {
    try {
      const res = await axios.put(`${taskUrl}/${id}`, taskData);
      toast.success("Task updated");
      return res.data;
    } catch (err) {
      toast.error(err.response?.data?.message || "Update failed");
      return rejectWithValue(err.response?.data || err.message);
    }
  }
);

// 6️⃣ Delete
export const deleteTask = createAsyncThunk(
  "tasks/deleteTask",
  async (id, { rejectWithValue }) => {
    try {
      const res = await axios.delete(`${taskUrl}/${id}`);
      toast.success("Task deleted");
      return id;                       // return id so we can filter it out
    } catch (err) {
      toast.error(err.response?.data?.message || "Delete failed");
      return rejectWithValue(err.response?.data || err.message);
    }
  }
);

// 7️⃣ Search / Filter (pagination + query)
export const fetchTaskSearch = createAsyncThunk(
  "tasks/fetchTaskSearch",
  async ({ page = 0, size, query } = {}, { rejectWithValue }) => {
    try {
      const params = { page };
      if (size) params.size = size;
      if (query) params.query = query;

      const res = await axios.get(query ? `${taskUrl}/search` : taskUrl, {
        params,
      });
      return res.data;
    } catch (err) {
      return rejectWithValue(err.response?.data || "Search failed");
    }
  }
);

/* -------------------------------------------------------------------------- */
/*                                  Slice                                     */
/* -------------------------------------------------------------------------- */

const taskSlice = createSlice({
  name: "tasks",
  initialState: {
    tasks: [],
    taskPrint: [],
    currentTask: null,
    loading: false,
    error: null,
    totalPages: 0,
    currentPage: 1,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      /* ---------- fetchTasks ---------- */
      .addCase(fetchTasks.pending, (s) => {
        s.loading = true;
      })
      .addCase(fetchTasks.fulfilled, (s, a) => {
        s.loading = false;
        s.tasks = a.payload.contents || [];
        s.totalPages = a.payload.totalPages || 0;
        s.currentPage = a.payload.currentPage || 1;
      })
      .addCase(fetchTasks.rejected, (s, a) => {
        s.loading = false;
        s.error = a.payload;
      })

      /* ---------- fetchTaskPrint ---------- */
      .addCase(fetchTaskPrint.pending, (s) => {
        s.loading = true;
      })
      .addCase(fetchTaskPrint.fulfilled, (s, a) => {
        s.loading = false;
        s.taskPrint = a.payload;
      })
      .addCase(fetchTaskPrint.rejected, (s, a) => {
        s.loading = false;
        s.error = a.payload;
      })

      /* ---------- fetchTaskById ---------- */
      .addCase(fetchTaskById.pending, (s) => {
        s.loading = true;
      })
      .addCase(fetchTaskById.fulfilled, (s, a) => {
        s.loading = false;
        s.currentTask = a.payload;
      })
      .addCase(fetchTaskById.rejected, (s, a) => {
        s.loading = false;
        s.error = a.payload;
      })

      /* ---------- addTask ---------- */
      .addCase(addTask.fulfilled, (s, a) => {
        s.tasks.push(a.payload);
      })

      /* ---------- updateTask ---------- */
      .addCase(updateTask.fulfilled, (s, a) => {
        const idx = s.tasks.findIndex((t) => t.id === a.payload.id);
        if (idx !== -1) s.tasks[idx] = a.payload;
        if (s.currentTask?.id === a.payload.id) s.currentTask = a.payload;
      })

      /* ---------- deleteTask ---------- */
      .addCase(deleteTask.fulfilled, (s, a) => {
        s.tasks = s.tasks.filter((t) => t.id !== a.payload);
      })

      /* ---------- fetchTaskSearch ---------- */
      .addCase(fetchTaskSearch.pending, (s) => {
        s.loading = true;
        s.error = null;
      })
      .addCase(fetchTaskSearch.fulfilled, (s, a) => {
        s.loading = false;
        s.tasks = a.payload.contents || [];
        s.totalPages = a.payload.totalPages || 0;
        s.currentPage = a.payload.currentPage || 1;
      })
      .addCase(fetchTaskSearch.rejected, (s, a) => {
        s.loading = false;
        s.error = a.payload;
      });
  },
});

export default taskSlice.reducer;
