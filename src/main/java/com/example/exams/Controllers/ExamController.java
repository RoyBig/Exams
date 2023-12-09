package com.example.exams.Controllers;


import com.example.exams.Model.Data.ProperDataModels.ExamDTO;
import com.example.exams.Model.Data.db.*;
import com.example.exams.Repositories.Db.*;
import com.example.exams.Services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.example.exams.Model.Data.ProperDataModels.ExamResponseDTO;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ExaminerService examinerService;

    @Autowired
    OpenQuestionService openQuestionService;

    @Autowired
    ClosedQuestionService closedQuestionService;

    @Autowired
    private AnswerClosedService answerClosedService;

    @Autowired
    private StudentclosedanswerRepository studentClosedAnswerRepository;

    @Autowired
    private StudentopenanswerRepository studentOpenAnswerRepository;

    @Autowired
    private StudentsEntityRepository studentRepository;

    @Autowired
    private OpenQuestionRepository openQuestionRepository;

    @Autowired
    private AnswerClosedRepository answerclosedRepository;

    @Autowired
    private ClosedQuestionRepository closedQuestionRepository;

    @PostMapping("/addExamQuestions")
    public String addExamQuestions(@RequestBody String body){

        String string = body.toString();
        try {
            String[] pairs = string.split("&");
            Map<String, String> map = new HashMap<>();
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    map.put(keyValue[0], keyValue[1]);
                }
            }

            String examDataString = map.get("examData");

            try {
                String decodedString = URLDecoder.decode(examDataString, "UTF-8");
                ObjectMapper objectMapper = new ObjectMapper().registerModule(new ParameterNamesModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new JavaTimeModule());
                ExamDTO exam = objectMapper.readValue(decodedString, ExamDTO.class);
                exam.setEgzamiantor(1);

                UserDetails user = null;
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                HttpSession session = request.getSession(false);
                if (session != null) {
                    user = (UserDetails) session.getAttribute("UsersEntity");
                }
                Collection<? extends GrantedAuthority> authorities= user.getAuthorities();
                boolean isExaminer = false;
                for (GrantedAuthority authority : authorities) {
                    if ("EXAMINER".equals(authority.getAuthority())) {
//                        examService.AddExam(exam);
                        break;
                    }
                    //DO USUNIECIA
                    if ("ADMIN".equals(authority.getAuthority())) {
//                        examService.AddExam(exam);
                        break;
                    }
                }
                return "redirect:/exams";
            } catch (UnsupportedEncodingException e) {
                System.out.println("Błąd dekodowania URL: " + e.getMessage());
            }

            return "redirect:/exams";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error-page";
        }
    }
    @PostMapping("/addExam/{egzaminator_egzaminator_id}")
    public String UpdateOpenQuestion(@ModelAttribute Exam exam, @PathVariable Integer egzaminator_egzaminator_id, @RequestParam Integer subjectid) {

        //Testy
        egzaminator_egzaminator_id = 1;

        Examiner examiner = examinerService.Get(egzaminator_egzaminator_id);
        exam.setConductingExaminer(examiner);

        Subject subject = subjectService.Get(subjectid);
        exam.setExamsSubject(subject);

        Exam addedExam = examService.AddExam(exam);
        return "redirect:/exams";
    }

    @PostMapping("/editExamDetails/{examId}")
    public String editExamDetails(@PathVariable Integer examId, @ModelAttribute Exam exam, @RequestParam("subject") Integer subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId.intValue());
        exam.setId(examId.intValue());
        exam.setExamsSubject(subject);
        examService.updateExam(exam);
        return "redirect:/exams";
    }

    @GetMapping("/editExam/{examId}")
    public ModelAndView editExam(@PathVariable Integer examId, Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        Exam exam = examService.GetExam(examId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editExam");
        modelAndView.addObject("subjects", subjects);
        modelAndView.addObject("exam", exam);
        model.addAttribute("examId", examId);
        return modelAndView;
    }

    @GetMapping("/showDoneExamUser/{examId}")
    public ModelAndView showDoneExamUser(@PathVariable Integer examId, Model model) {
        Exam exam = examService.GetExam(examId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showDoneExamUsers");
        modelAndView.addObject("exam", exam);
        model.addAttribute("examId", examId);
        return modelAndView;
    }

    @GetMapping("/confirmExamDeletion/{examId}")
    public ModelAndView deleteExam(@PathVariable Integer examId, Model model) {
//        boolean deleted = examService.deleteExam(examId);
//        if (deleted) {
//            return ResponseEntity.ok("Exam deleted successfully");
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found");
        Exam exam = examService.GetExam(examId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteExam");
        modelAndView.addObject("exam", exam);
        model.addAttribute("examId", examId);
//        examService.deleteExam(examId);
        return modelAndView;
    }

    @PostMapping("/deleteExam/{examId}")
    public ResponseEntity<String> deleteExam(@RequestParam Integer examId) {
        boolean deleted = examService.deleteExam(examId);
        if (deleted) {
            return ResponseEntity.ok("Exam deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found");
    }

    @GetMapping("/showExamDetails/{examId}")
    public ModelAndView getExamDetails(@PathVariable String examId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("examDetails");

        Exam exam = examService.GetExam(Integer.parseInt(examId));

        modelAndView.addObject("exam", examService.GetExam(Integer.parseInt(examId)));
        modelAndView.addObject("listOpenQuestions", openQuestionService.getAllByExamId(Integer.parseInt(examId)));

        return modelAndView;
    }

    @GetMapping("/solveExam/{examId}")
    public ModelAndView getExamToSolve(@PathVariable String examId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("solveExam");

        Exam exam = examService.GetExam(Integer.parseInt(examId));
        LocalDate startDate = exam.getStartDate();
        LocalTime startTime = exam.getStartTime();
        LocalDate endDate = exam.getEndDate();
        LocalTime endTime = exam.getEndTime();

        if (LocalDate.now().isAfter(startDate) || LocalDate.now().equals(startDate)) {
            if (LocalTime.now().isAfter(startTime) || LocalTime.now().equals(startTime))
                modelAndView.addObject("timeStarts", true);
            else
                modelAndView.addObject("timeStarts", false);
        } else
            modelAndView.addObject("timeStarts", false);

        if (LocalDate.now().isBefore(endDate) || LocalDate.now().equals(endDate)) {
            if (LocalTime.now().isBefore(endTime) || LocalTime.now().equals(endTime))
                modelAndView.addObject("timeEnds", false);
            else
                modelAndView.addObject("timeEnds", true);
        } else
            modelAndView.addObject("timeEnds", true);

        modelAndView.addObject("exam", examService.GetExam(Integer.parseInt(examId)));
        modelAndView.addObject("listOpenQuestions", openQuestionService.getAllByExamId(Integer.parseInt(examId)));

        List<Closedquestion> listClosedQuestions = closedQuestionService.getAllByExamId(Integer.parseInt(examId));
        List<List<Answerclosed>> closedAnswers = new ArrayList<>();


        for (int i = 0; i < listClosedQuestions.size(); i++)
            closedAnswers.add(answerClosedService.getAllByQuestionId(listClosedQuestions.get(i).getId()));

        modelAndView.addObject("listClosedQuestions", closedQuestionService.getAllByExamId(Integer.parseInt(examId)));
        modelAndView.addObject("closedAnswers", closedAnswers);

        return modelAndView;
    }

    private boolean processOpenAnswers(Map<String, String> openAnswers, UserDetails userDetails) {
        Student student = studentRepository.findStudentByLogin(userDetails.getUsername());

        openAnswers.forEach((questionId, answer) -> {
            Studentopenanswer openAnswer = new Studentopenanswer();
            OpenQuestion openquestion = openQuestionRepository.findById(Integer.parseInt(questionId)).orElse(null);
            if (openquestion != null && student != null) {
                openAnswer.setOpenquestionQuestionid(openquestion);
                openAnswer.setDescription(answer);
                openAnswer.setStudentStudent(student);
                openAnswer.setDate(LocalDate.now());
                studentOpenAnswerRepository.save(openAnswer);
            }
        });
        return true;

    }

    private boolean processClosedAnswers(Map<String, String[]> closedAnswers, UserDetails userDetails) {
        Student student = studentRepository.findStudentByLogin(userDetails.getUsername());
        if (student == null) {
            return false; // Nie ma studenta o ID 1
        }

        for (Map.Entry<String, String[]> entry : closedAnswers.entrySet()) {
            Integer questionId = Integer.parseInt(entry.getKey());
            Closedquestion closedQuestion = closedQuestionRepository.findById(questionId).orElse(null);
            for (String answerId : entry.getValue()) {
                Answerclosed answerclosed = answerclosedRepository.findById(Integer.parseInt(answerId)).orElse(null);
                if (answerclosed == null) {
                    return false;
                }

                Studentclosedanswer closedAnswer = new Studentclosedanswer();
                closedAnswer.setAnswerclosedAnswerid(answerclosed);
                closedAnswer.setClosedquestionQuestionid(closedQuestion);
                closedAnswer.setStudentStudent(student);
                closedAnswer.setDate(LocalDate.now());

                boolean isCorrect = answerclosed.isCorrect();
                closedAnswer.setCorrectness(isCorrect);
                studentClosedAnswerRepository.save(closedAnswer);
            }
        }

        return true;

    }

    @PostMapping("/saveResolvedExam")
    public String saveExam(@ModelAttribute ExamResponseDTO examResponse) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showExams");
        UserDetails user = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            modelAndView.addObject("UsersEntity", session.getAttribute("UsersEntity"));
            user = (UserDetails) session.getAttribute("UsersEntity");
        }

        boolean openAnswersSaved = processOpenAnswers(examResponse.getOpenAnswers(), user);
        boolean closedAnswersSaved = processClosedAnswers(examResponse.getClosedAnswers(), user);
        if (!openAnswersSaved || !closedAnswersSaved) {
            modelAndView.addObject("error", "Nie udało się zapisać wszystkich odpowiedzi.");
        }



        return "redirect:/exams";
    }

    @PostMapping("/processForm")
    public String processForm(@RequestParam("action") String action) {

        Integer examId = Integer.parseInt(action.substring(action.indexOf(':') + 1));
        if (action.startsWith("show:")) {
            return "redirect:/showExamDetails/" + examId;
        } else if (action.startsWith("edit:")) {
            return "redirect:/editExam/" + examId;
        } else if (action.startsWith("delete:")) {
            return "redirect:/confirmExamDeletion/" + examId;
        } else if (action.startsWith("solveExam:")) {
            return "redirect:/solveExam/" + examId;
        }else if (action.startsWith("showDoneExamUser:")){
            return "redirect:/showDoneExamUser/" + examId;
        }
        return "error";
    }
}