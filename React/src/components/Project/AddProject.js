import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/ProjectActions";
import classnames from "classnames";

class AddProject extends Component {
  constructor() {
    super();

    this.today = this.calculateLocalDate();

    this.state = {
      projectName: "",
      projectIdentifier: "",
      description: "",
      startDate: this.today,
      endDate: "",
      errors: {},
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  //life cycle hooks
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  calculateLocalDate() {
    let date = new Date();
    let thisLocale = Intl.DateTimeFormat().resolvedOptions().locale;
    let thisTimeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;
    let formatter = new Intl.DateTimeFormat(thisLocale, {
      year: "numeric",
      month: "numeric",
      day: "numeric",
      timeZone: thisTimeZone,
    });
    let today = formatter.formatToParts(date);
    if (today[2].value < "10") {
      today[2].value = "0" + today[2].value;
    }
    if (today[0].value < "10") {
      today[0].value = "0" + today[0].value;
    }
    let todayFormatted =
      today[4].value + "-" + today[0].value + "-" + today[2].value;
    return todayFormatted;
  }

  // calculateLocalDate() {
  //   let thisLocale = Intl.DateTimeFormat().resolvedOptions().locale;
  //   let thisTimeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;
  //   let d = new Date();
  //   let today = d.toLocaleString(thisLocale, { timeZone: thisTimeZone });
  //   return today.split(",")[0];
  // }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newProject = {
      projectName: this.state.projectName,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      startDate: this.state.startDate,
      endDate: this.state.endDate,
    };
    this.props.createProject(newProject, this.props.history);
  }

  render() {
    const { errors } = this.state;

    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Create Project form</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.projectName,
                      })}
                      placeholder="Project Name"
                      name="projectName"
                      value={this.state.projectName}
                      onChange={this.onChange}
                    />
                    {errors.projectName && (
                      <div className="invalid-feedback">
                        {errors.projectName}
                      </div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.projectIdentifier,
                      })}
                      placeholder="Unique Project ID"
                      name="projectIdentifier"
                      value={this.state.projectIdentifier}
                      onChange={this.onChange}
                    />
                    {errors.projectIdentifier && (
                      <div className="invalid-feedback">
                        {errors.projectIdentifier}
                      </div>
                    )}
                  </div>
                  <div className="form-group">
                    <textarea
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.description,
                      })}
                      placeholder="Project Description"
                      name="description"
                      value={this.state.description}
                      onChange={this.onChange}
                    />
                    {errors.description && (
                      <div className="invalid-feedback">
                        {errors.description}
                      </div>
                    )}
                  </div>
                  <h6>Start Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="startDate"
                      min={this.today}
                      value={this.state.startDate}
                      onChange={this.onChange}
                    />
                  </div>
                  <h6>Estimated End Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="endDate"
                      min={this.state.startDate}
                      value={this.state.endDate}
                      onChange={this.onChange}
                    />
                  </div>

                  <input type="submit" className="btn primary btn-block mt-4" />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

AddProject.propTypes = {
  createProject: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  errors: state.errors,
});

export default connect(mapStateToProps, { createProject })(AddProject);
