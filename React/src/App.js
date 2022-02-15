import "./App.css";
import React from "react";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import UpdateProject from "./components/Project/UpdateProject";
import { useLocation } from "react-router-dom";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import AddProjectTask from "./components/ProjectBoard/ProjectTasks/AddProjectTask";
import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagement/Register";
import Login from "./components/UserManagement/Login";
import UpdateProjectTask from "./components/ProjectBoard/ProjectTasks/UpdateProjectTask";
import SetJWTToken from "./SecurityUtils/SetJWTToken";
import jwt_decode from "jwt-decode";
import { SET_CURRENT_USER } from "./actions/Types";
import store from "./store";
import { logout } from "./actions/SecurityActions";
import SecureRoute from "./SecurityUtils/SecureRoute";
const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  SetJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken,
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

function App() {
  const location = useLocation;
  return (
    <div className="App">
      <Router location={location} key={location.pathname}>
        <Header />

        <Route exact path="/" component={Landing} />
        <Route exact path="/register" component={Register} />
        <Route exact path="/login" component={Login} />

        <Switch>
          <SecureRoute exact path="/dashboard" component={Dashboard} />
          <SecureRoute exact path="/addProject" component={AddProject} />
          <SecureRoute
            exact
            path="/updateProject/:id"
            component={UpdateProject}
          />
          <SecureRoute
            exact
            path="/projectBoard/:id"
            component={ProjectBoard}
          />
          <SecureRoute
            exact
            path="/addProjectTask/:id"
            component={AddProjectTask}
          />
          <SecureRoute
            exact
            path="/updateProjectTask/:backlog_id/:pt_id"
            component={UpdateProjectTask}
          />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
