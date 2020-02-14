import { HTTP } from '../utils/http.js'
class Resume extends HTTP {
  getResumeByApplicantId(id, sCallback) {
    this.request({
      url: '/resume/getResumeByApplicantId',
      method: 'get',
      data: {
        id: id
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getOneById(id, sCallback) {
    this.request({
      url: '/resume/getOneById',
      method: 'get',
      data: {
        id: id
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getResumeEducationById(id,sCallback){
    this.request({
      url: '/resume/getResumeEducationById',
      method: 'get',
      data: {
        id: id
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getResumeProjectById(id, sCallback) {
    this.request({
      url: '/resume/getResumeProjectById',
      method: 'get',
      data: {
        id: id
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getResumeExperienceById(id, sCallback) {
    this.request({
      url: '/resume/getResumeExperienceById',
      method: 'get',
      data: {
        id: id
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  getResumeByApplicantId(applicantId,sCallback){
    this.request({
      url: '/resume/getResumeByApplicantId',
      method: 'get',
      data: {
        applicantId:applicantId
      },
      success: (res) => {
        sCallback(res)
      }
    })
  } 
  insertResume(resume, sCallback) {
    this.request({
      url: '/resume/insertResume',
      method: 'post',
      data: {
        applicantId: resume.applicantId,
        name: resume.name,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  updateResume(resume, sCallback) {
    this.request({
      url: '/resume/updateResume',
      method: 'put',
      data: {
        id: resume.id,
        applicantId: resume.applicantId,
        position: resume.position,
        salary: resume.salary,
        city: resume.city,
        name: resume.name,
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  deleteResume(id, sCallback) {
    this.request({
      url: '/resume/deleteResume/' + id,
      method: 'delete',
      data: {},
      success: (res) => {
        sCallback(res)
      }
    })
  }
  insertResumeEducation(resumeEducation, sCallback) {
    this.request({
      url: '/resume/insertResumeEducation',
      method: 'post',
      data: {
        resumeId: resumeEducation.resumeId,
        school: resumeEducation.school,
        education: resumeEducation.education,
        profession: resumeEducation.profession,
        startYear: resumeEducation.startYear,
        endYear:resumeEducation.endYear
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  updateResumeEducation(resumeEducation, sCallback) {
    this.request({
      url: '/resume/updateResumeEducation',
      method: 'put',
      data: {
        id: resumeEducation.id,
        resumeId: resumeEducation.resumeId,
        school: resumeEducation.school,
        education: resumeEducation.education,
        profession: resumeEducation.profession,
        startYear: resumeEducation.startYear,
        endYear: resumeEducation.endYear
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  deleteResumeEducation(id, sCallback) {
    this.request({
      url: '/resume/deleteResumeEducation/'+id,
      method: 'delete',
      data: {},
      success: (res) => {
        sCallback(res)
      }
    })     
  }
  insertResumeExperience(resumeExperience, sCallback) {
    this.request({
      url: '/resume/insertResumeExperience',
      method: 'post',
      data: {
        resumeId: resumeExperience.resumeId,
        company: resumeExperience.company,
        job: resumeExperience.job,
        department: resumeExperience.department,
        startTime: resumeExperience.startTime,
        endTime: resumeExperience.endTime,
        content: resumeExperience.content
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  updateResumeExperience(resumeExperience, sCallback) {
    this.request({
      url: '/resume/updateResumeExperience',
      method: 'put',
      data: {
        id: resumeExperience.id,
        resumeId: resumeExperience.resumeId,
        company: resumeExperience.company,
        job: resumeExperience.job,
        department: resumeExperience.department,
        startTime: resumeExperience.startTime,
        endTime: resumeExperience.endTime,
        content:resumeExperience.content
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  deleteResumeExperience(id, sCallback) {
    this.request({
      url: '/resume/deleteResumeExperience/' + id,
      method: 'delete',
      data: {},
      success: (res) => {
        sCallback(res)
      }
    })
  }
  insertResumeProject(resumeProject, sCallback) {
    this.request({
      url: '/resume/insertResumeProject',
      method: 'post',
      data: {
        resumeId: resumeProject.resumeId,
        name: resumeProject.name,
        personJob: resumeProject.personJob,
        startTime: resumeProject.startTime,
        endTime: resumeProject.endTime,
        content: resumeProject.content
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  updateResumeProject(resumeProject, sCallback) {
    this.request({
      url: '/resume/updateResumeProject',
      method: 'put',
      data: {
        id: resumeProject.id,
        resumeId: resumeProject.resumeId,
        name: resumeProject.name,
        personJob: resumeProject.personJob,
        startTime: resumeProject.startTime,
        endTime: resumeProject.endTime,
        content: resumeProject.content
      },
      success: (res) => {
        sCallback(res)
      }
    })
  }
  deleteResumeProject(id, sCallback) {
    this.request({
      url: '/resume/deleteResumeProject/' + id,
      method: 'delete',
      data: {},
      success: (res) => {
        sCallback(res)
      }
    })
  }
}
export { Resume }