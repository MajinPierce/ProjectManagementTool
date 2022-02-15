import React from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteProject } from "../../actions/ProjectActions";

const ProjectItem = (props) => {
  const deleteClickHandler = (event) => {
    props.deleteProject(props.project.projectIdentifier);
  };

  return (
    <div className="project-item">
      <div className="card card-body mb-3">
        <div className="row">
          <div className="col-2">
            <span className="mx-auto">{props.project.projectIdentifier}</span>
          </div>
          <div className="col-lg-6 col-md-4 col-8">
            <h3>{props.project.projectName}</h3>
            <p>{props.project.description}</p>
          </div>
          <div className="col-md-4 d-lg-block">
            <ul className="list-group">
              <Link
                to={`/projectBoard/${props.project.projectIdentifier}`}
                style={{ textDecoration: "none" }}
              >
                <li className="list-group-item board">
                  <i className="board"> Project Board </i>
                </li>
              </Link>
              <Link
                to={`/updateProject/${props.project.projectIdentifier}`}
                style={{ textDecoration: "none" }}
              >
                <li className="list-group-item update">
                  <i className="edit"> Update Project Info</i>
                </li>
              </Link>
              <li
                className="list-group-item delete"
                onClick={deleteClickHandler}
              >
                <i className="delete"> Delete Project</i>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

ProjectItem.propTypes = {
  deleteProject: PropTypes.func.isRequired,
};

export default connect(null, { deleteProject })(ProjectItem);
